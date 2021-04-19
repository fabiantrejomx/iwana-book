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

  @Override
  public List<Book> getBooksByYearOfPublication(String order, Integer limit, Integer offset) {

    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Book> query = builder.createQuery(Book.class);
    final Root<Book> root = query.from(Book.class);

    query.select(root);

    if ("asc".equals(order)) {
      query.orderBy(builder.asc(root.get(Book_.releaseDate)));
    } else if ("desc".equals(order)) {
      query.orderBy(builder.desc(root.get(Book_.releaseDate)));
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

  /*Buscar aquellos libros donde participó X autor haciendo match ya sea con su nombre o apellidos
  *
  * select book.title from book
          inner join book_author on book_author.book_id = book.id
          inner join author on book_author.author_id = author.id
    where author.name = "author-1" or author.first_name = "fn-1" or author.second_name="sn-name";
  *
  * */
  @Override
  public List<String> getBookByAuthor(final String author) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<String> query = builder.createQuery(String.class);
    final Root<Book> root = query.from(Book.class);

    final Join<Book, Author> authorJoin = root.join(Book_.authors);
    query
        .select(root.get(Book_.title))
        .where(
            builder.or(
                builder.equal(authorJoin.get(Author_.name), author),
                builder.equal(authorJoin.get(Author_.firstName), author),
                builder.equal(authorJoin.get(Author_.secondName), author)));

    return em.createQuery(query).getResultList();
  }

  /*Donde el precio esté en el rango de precioMinimo a precioMáximo
  * select book.title from book
  where price between 300 AND 599;*/
  @Override
  public List<String> getBooksByPrice(final BigDecimal priceMin, final BigDecimal priceMax) {

    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<String> query = builder.createQuery(String.class);
    final Root<Book> root = query.from(Book.class);

    query
        .select(root.get(Book_.title))
        .where(builder.between(root.get(Book_.price), priceMin, priceMax));

    return em.createQuery(query).getResultList();
  }

  /*-- Buscar aquellos libros en un rango de fechas (fechaInicial, fechaFinal) de publicación
  select book.title from book
  where date_publication between "1650-04-01" AND "1800-05-01";*/

  @Override
  public List<String> getBooksByDate(final Date startDate, final Date endDate) {

    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<String> query = builder.createQuery(String.class);
    final Root<Book> root = query.from(Book.class);

    query
        .select(root.get(Book_.title))
        .where(builder.between(root.get(Book_.releaseDate), startDate, endDate));

    return em.createQuery(query).getResultList();
  }

  /*-- Filtrar los libros por categoría.
  select book.title from category
  inner join book on category.id = book.category_id
  where category.name= "Arte";*/
  @Override
  public List<String> getBooksByCategory(final String category) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<String> query = builder.createQuery(String.class);
    final Root<Book> root = query.from(Book.class);

    final Join<Book, Category> bookJoinCategory = root.join(Book_.categories);
    query
        .select(root.get(Book_.title))
        .where(builder.equal(bookJoinCategory.get(Category_.name), category));

    return em.createQuery(query).getResultList();
  }

  /*-- Número de libros que existe de x categoría
  select category.name, COUNT(book.category_id) from book
      inner join category on category.id = book.category_id
  where category.name= "Arte";*/
  @Override
  public Long getAmountOfBooksByCategory(final String category) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<Book> root = query.from(Book.class);
    final Join<Book, Category> bookJoinCategory = root.join(Book_.categories);
    query
        .select(builder.count(root.get(Book_.id)))
        .where(builder.equal(bookJoinCategory.get(Category_.name), category));
    return em.createQuery(query).getSingleResult();
  }

  /*-- Dado un número, buscar aquellos libros donde contengan ese  número de autores
  select bookTitle from
      (select COUNT(book_author.book_id) as amount, book.title as bookTitle from book_author
          inner join book on book_author.book_id = book.id
       GROUP BY (book_author.book_id)) subquery
  where amount =1;*/

  @Override
  public List<Book> getBooksByAmountAuthors(final long authors) {
    // create the outer query
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery cq = cb.createQuery(Book.class);
    Root root = cq.from(Book.class);

    // count books written by an author
    Subquery sub = cq.subquery(Long.class);
    Root subRoot = sub.from(Book.class);

    final Join<Book, Author> bookJoinBookAuthor = subRoot.join(Book_.authors);

    sub.select(cb.count(subRoot.get(Book_.id)));
    sub.where(cb.equal(root.get(Book_.id), bookJoinBookAuthor.get(Author_.id)));

    // check the result of the subquery
    cq.where(cb.greaterThanOrEqualTo(sub, authors));

    TypedQuery query = em.createQuery(cq);
    return query.getResultList();
  }

  /*Filtrar libros ascendente y descendente por número de páginas que contiene el libro
   * */

  @Override
  public List<Book> getBooksByPages(String order, Integer limit, Integer offset) {

    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<Book> query = builder.createQuery(Book.class);
    final Root<Book> root = query.from(Book.class);

    query.select(root);

    if ("asc".equals(order)) {
      query.orderBy(builder.asc(root.get(Book_.pages)));
    } else if ("desc".equals(order)) {
      query.orderBy(builder.desc(root.get(Book_.pages)));
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
