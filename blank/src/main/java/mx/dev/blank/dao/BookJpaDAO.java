package mx.dev.blank.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookJpaDAO implements BookDAO {

  @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
  private EntityManager em;

  /* create */
  @Override
  public void create(final Book book) {
    em.persist(book);
  }

  /* update */
  @Override
  public void update(final Book book) {
    em.merge(book);
  }

  /* Delete */
  @Override
  public void delete(final Book book) {
    em.remove(book);
  }

  /* Select * FROM books WHERE id = ? */
  @Override
  public Book findById(final int id) {
    return em.find(Book.class, id);
  }

  /* SELECT * FROM books ORDER BY ? ASC|DESC LIMIT ?, ?
   *
   * 1. Mostrar todos los libros ordenados por año de publicación ascendente, los resultados deberán de ser paginados. (quedando 2 libros por página)
   * 2. Mostrar libros descendentes por año de publicación. (quedando 2 libros por página)
   * 9. Filtrar libros ascendente y descendente por número de páginas que contiene el libro.
   *
   * */
  @Override
  public List<Book> findBooks(
      final String sortField, final SortingOrder order, final Integer limit, final Integer offset) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Book> query = builder.createQuery(Book.class);
    final Root<Book> root = query.from(Book.class);

    // This should be loaded using expands
    root.fetch(Book_.categories);
    root.fetch(Book_.authors);

    if (StringUtils.isNotBlank(sortField)) {
      final Path<?> sortFieldValue = getSortField(root, sortField);
      if (SortingOrder.ASC == order) {
        query.orderBy(builder.asc(sortFieldValue));
      } else {
        query.orderBy(builder.desc(sortFieldValue));
      }
    }

    final TypedQuery<Book> typedQuery = em.createQuery(query);

    if (offset != null) {
      typedQuery.setFirstResult(offset);
    }

    if (limit != null) {
      typedQuery.setMaxResults(limit);
    }

    return typedQuery.getResultList();
  }

  private Path<?> getSortField(final Root<Book> root, final String sortField) {
    switch (sortField) {
      case "title":
        return root.get(Book_.title);
      case "pages":
        return root.get(Book_.pages);
      case "releaseDate":
        return root.get(Book_.releaseDate);
      default:
        return root.get(Book_.id);
    }
  }

  /*
   * SELECT * from book
   * INNER JOIN book_author ON book_author.book_id = book.id
   * INNER JOIN author ON book_author.author_id = author.id
   * WHERE author.name = "author-1" OR author.first_name = "fn-1" OR author.second_name="sn-name";
   *
   * 3. Buscar aquellos libros donde participó X autor haciendo match ya sea con su nombre o apellidos
   *
   * */
  @Override
  public List<Book> getBookByAuthor(final String author) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Book> query = builder.createQuery(Book.class);
    final Root<Book> root = query.from(Book.class);

    // This should be loaded using expands
    root.fetch(Book_.categories);
    root.fetch(Book_.authors);

    final Join<Book, Author> authorJoin = root.join(Book_.authors);
    query
        .select(root)
        .where(
            builder.or(
                builder.equal(authorJoin.get(Author_.name), author),
                builder.equal(authorJoin.get(Author_.firstName), author),
                builder.equal(authorJoin.get(Author_.secondName), author)))
        .distinct(true);

    return em.createQuery(query).getResultList();
  }

  /* SELECT  * FROM book WHERE price BETWEENbetween ? AND ?;
   *
   * 4. Donde el precio esté en el rango de precioMinimo a precioMáximo
   *
   * */
  @Override
  public List<Book> getBooksByPrice(final BigDecimal priceMin, final BigDecimal priceMax) {

    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Book> query = builder.createQuery(Book.class);
    final Root<Book> root = query.from(Book.class);

    // This should be loaded using expands
    root.fetch(Book_.categories);
    root.fetch(Book_.authors);

    query
        .select(root)
        .where(builder.between(root.get(Book_.price), priceMin, priceMax))
        .distinct(true);

    return em.createQuery(query).getResultList();
  }

  /* SELECT book.* FROM book
   * INNER JOIN book_author ON book_author.book_id = book.id
   * INNER JOIN author ON book_author.author_id = author.id
   * GROUP BY book.id
   * HAVING Count(*) = ?
   *
   * 5. Dado un número, buscar aquellos libros donde contengan ese  número de autores.
   *
   * */

  // TODO Fix grouping error
  @Override
  public List<Book> getBooksByAmountAuthors(final long authors) {

    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Book> query = builder.createQuery(Book.class);
    final Root<Book> root = query.from(Book.class);

    // This should be loaded using expands
    root.fetch(Book_.categories);
    root.fetch(Book_.authors);

    final Join<Book, Author> authorJoin = root.join(Book_.authors);

    query
        .select(root)
        .groupBy(root.get(Book_.id))
        .having(builder.greaterThan(builder.count(authorJoin), authors));

    return em.createQuery(query).getResultList();
  }

  /* SELECT * FROM book WHERE date_publication BETWEEN ? AND ?;
   *
   * 6. Buscar aquellos libros en un rango de fechas (fechaInicial, fechaFinal) de publicación
   *
   * */

  @Override
  public List<Book> getBooksByDate(final Date startDate, final Date endDate) {

    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Book> query = builder.createQuery(Book.class);
    final Root<Book> root = query.from(Book.class);

    // This should be loaded using expands
    root.fetch(Book_.categories);
    root.fetch(Book_.authors);

    query
        .select(root)
        .where(builder.between(root.get(Book_.releaseDate), startDate, endDate))
        .distinct(true);

    return em.createQuery(query).getResultList();
  }

  /* SELECT Count(*) FROM book
   *  INNER JOIN book_category ON book_category.book_id = book.id
   *  INNER JOIN category ON book_category.category_id = category.id
   *  WHERE category.name = "Arte";
   *
   * 7. Número de libros que existe de x categoría
   *
   */
  @Override
  public Long getAmountOfBooksByCategory(final String category) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<Book> root = query.from(Book.class);
    final Join<Book, Category> categoryJoin = root.join(Book_.categories);
    query
        .select(builder.count(root.get(Book_.id)))
        .where(builder.equal(categoryJoin.get(Category_.name), category));
    return em.createQuery(query).getSingleResult();
  }

  /* SELECT book.* FROM category
   * INNER JOIN book_category ON book_category.book_id = book.id
   * INNER JOIN category ON book_category.category_id = category.id
   * WHERE category.name = ?;
   *
   * 8. Número de libros que existe de x categoría
   *
   * */
  @Override
  public List<Book> getBooksByCategory(final String category) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Book> query = builder.createQuery(Book.class);
    final Root<Book> root = query.from(Book.class);

    // This should be loaded using expands
    root.fetch(Book_.categories);
    root.fetch(Book_.authors);

    final Join<Book, Category> bookJoinCategory = root.join(Book_.categories);

    query
        .select(root)
        .where(builder.equal(bookJoinCategory.get(Category_.name), category))
        .distinct(true);

    return em.createQuery(query).getResultList();
  }

  /*Ranking by book
  * select avg(ranking.score)  from ranking
     inner join book on ranking.book_id = book.id
    where ranking.book_id=2
  * */

  @Override
  public Double getRankingByBook(final long book_id) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Double> query = builder.createQuery(Double.class);
    final Root<Ranking> root = query.from(Ranking.class);

    query
        .select(builder.avg(root.get(Ranking_.score)))
        .where(builder.equal(root.get(Ranking_.book), book_id));

    return em.createQuery(query).getSingleResult();
  }
}
