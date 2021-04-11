package mx.dev.blank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.*;
import mx.dev.blank.web.request.BookRequest;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookJpaDAO implements BookDAO {

    @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
    private EntityManager em;

    @Override
    public List<Book> getBooksByYearOfPublication(String order, Integer limit, Integer offset) {

        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        query.select(root);

        if ("asc".equals(order)) {
            query.orderBy(builder.asc(root.get(Book_.datePublication)));
        } else if ("desc".equals(order)) {
            query.orderBy(builder.desc(root.get(Book_.datePublication)));
        }

        final TypedQuery<Book> typedQuery= em.createQuery(query);

        if(offset != null){
            typedQuery.setFirstResult(offset);
        }

        if(limit != null){
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
        final Root<BookAuthor> root = query.from(BookAuthor.class);

        final Join<BookAuthor, Book> bookJoinBookAuthor=root.join(BookAuthor_.book);
        final Join<BookAuthor, Author> authorJoinBookAuthor=root.join(BookAuthor_.author);
        query
        .select(bookJoinBookAuthor.get(Book_.title))
        .where(
                builder.or(
                        builder.equal(authorJoinBookAuthor.get(Author_.name), author),
                        builder.equal(authorJoinBookAuthor.get(Author_.first_name), author),
                        builder.equal(authorJoinBookAuthor.get(Author_.second_name), author)
                ));

        return em.createQuery(query).getResultList();
    }

    /*Donde el precio esté en el rango de precioMinimo a precioMáximo
    * select book.title from book
    where price between 300 AND 599;*/
    @Override
    public List<String> getBooksByPrice(
            final float priceMin,
            final float priceMax) {

        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<String> query = builder.createQuery(String.class);
        final Root<Book> root = query.from(Book.class);

        query.select(root.get(Book_.title))

        .where(builder.between(root.get(Book_.price), priceMin, priceMax));

        return em.createQuery(query).getResultList();
    }

    /*-- Buscar aquellos libros en un rango de fechas (fechaInicial, fechaFinal) de publicación
        select book.title from book
        where date_publication between "1650-04-01" AND "1800-05-01";*/

    @Override
    public List<String> getBooksByDate(
            final Date startDate,
            final Date endDate) {

        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<String> query = builder.createQuery(String.class);
        final Root<Book> root = query.from(Book.class);

        query.select(root.get(Book_.title))

                .where(builder.between(root.get(Book_.datePublication), startDate, endDate));

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

        final Join<Book, Category> bookJoinCategory=root.join(Book_.category);
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
        final Join<Book, Category> bookJoinCategory=root.join(Book_.category);
        query
                .select(
                        builder.count(
                                root.get(Book_.id)))
                .where(
                        builder.equal(
                                bookJoinCategory.get(Category_.name), category));
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
        Root subRoot = sub.from(BookAuthor.class);

        final Join<BookAuthor, Book> bookJoinBookAuthor = subRoot.join(BookAuthor_.book);

        sub.select(cb.count(subRoot.get(BookAuthor_.id)));
        sub.where(cb.equal(root.get(Book_.id), bookJoinBookAuthor.get(Book_.id)));

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

        final TypedQuery<Book> typedQuery= em.createQuery(query);

        if(offset != null){
            typedQuery.setFirstResult(offset);
        }

        if(limit != null){
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

    /*create*/

    public Book createBook(final BookRequest bookRequest){
        Book book = Book.newBook(bookRequest.getTitle(),
                bookRequest.getPages(),
                bookRequest.getIsbn(),
                bookRequest.getPrice(),
                bookRequest.getSummary(),
                bookRequest.getEditorial(),
                bookRequest.getDatePublication(),
                bookRequest.getCategory());
        System.out.println(book);

        em.persist(book);

        return book;
    }


    /*update*/

    public Integer updateBook(final Integer bookId, final BookRequest bookRequest){
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        // create update
        CriteriaUpdate<Book> update = cb.
                createCriteriaUpdate(Book.class);

        // set the root class
        Root e = update.from(Book.class);

        // set update and where clause
        update.set("title", bookRequest.getTitle());
        update.set("pages", bookRequest.getPages());
        update.set("isbn", bookRequest.getIsbn());
        update.set("price", bookRequest.getPrice());
        update.set("summary", bookRequest.getSummary());
        update.set("editorial", bookRequest.getEditorial());
        update.set("datePublication", bookRequest.getDatePublication());

        update.where(cb.equal(e.get("id"), bookId));

        // perform update
        return this.em.createQuery(update).executeUpdate();
    }


    /*Delete*/
    public void deleteBook(final int bookId) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = cb.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        query.where(cb.equal(root.get(Book_.id), bookId));

        this.em.remove(this.em.createQuery(query).getSingleResult());
    }

}
