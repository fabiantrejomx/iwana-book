package mx.dev.blank.dao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.*;
import mx.dev.blank.web.controller.request.BookFilterRequest;
import mx.dev.blank.web.controller.request.BookRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class BookJpaDAO implements BookDAO {

    @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
    private EntityManager em;

    @Override
    public void add(final Book book) {
        em.persist(book);
    }


    @Override
    public void update(final Book book) {
        em.merge(book);
    }


    @Override
    public void delete(final int bookId) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaUpdate<Book> update = builder.createCriteriaUpdate(Book.class);
        final Root<Book> root = update.from(Book.class);

        update.set("true", root.get(Book_.deleted));
        update.where(builder.equal(root.get(Book_.id), bookId));

        em.createQuery(update).executeUpdate();
    }


    @Override
    public Book findById(final long courseId) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        query.where(builder.equal(root.get(Book_.id), courseId));

        return HibernateHelper.getSingleResult(em, query);
    }

    /*
     * Get a list of books by any field EXCEPT summary
     * 1. Get a list of books by publicationDate order by asc
     * 2. Get a List of books by publicationDate order by desc
     * 9. Get a list of books by pagesNumber order by asc and desc
     */

    @Override
    public List<Book> getBooksByAnyFieldOrderByAsc(final BookRequest request, final String field) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        if (request != null) {

            switch (field) {
                case "title":
                    query.select(root).orderBy(builder.asc(root.get(Book_.title)));
                    break;
                case "pagesNumber":
                    query.select(root).orderBy(builder.asc(root.get(Book_.pagesNumber)));
                    break;
                case "authors":
                    query.select(root).orderBy(builder.asc(root.get(Book_.authors)));
                    break;
                case "editorial":
                    query.select(root).orderBy(builder.asc(root.get(Book_.editorial)));
                    break;
                case "isbn":
                    query.select(root).orderBy(builder.asc(root.get(Book_.isbn)));
                    break;
                case "category":
                    query.select(root).orderBy(builder.asc(root.get(Book_.category)));
                    break;
                case "price":
                    query.select(root).orderBy(builder.asc(root.get(Book_.price)));
                    break;
                case "summary":
                    query.select(root).orderBy(builder.asc(root.get(Book_.summary)));
                    break;
                case "publicationDate":
                    query.select(root).orderBy(builder.asc(root.get(Book_.publicationDate)));
                    break;
                default:
                    break;
            }
        }

        return  HibernateHelper.getResults(em, query);
    }

    @Override
    public List<Book> getBooksByAnyFieldOrderByDesc(final BookRequest request, final String field) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        if (request != null) {

            switch (field) {
                case "title":
                    query.select(root).orderBy(builder.desc(root.get(Book_.title)));
                    break;
                case "pagesNumber":
                    query.select(root).orderBy(builder.desc(root.get(Book_.pagesNumber)));
                    break;
                case "authors":
                    query.select(root).orderBy(builder.desc(root.get(Book_.authors)));
                    break;
                case "editorial":
                    query.select(root).orderBy(builder.desc(root.get(Book_.editorial)));
                    break;
                case "isbn":
                    query.select(root).orderBy(builder.desc(root.get(Book_.isbn)));
                    break;
                case "category":
                    query.select(root).orderBy(builder.desc(root.get(Book_.category)));
                    break;
                case "price":
                    query.select(root).orderBy(builder.desc(root.get(Book_.price)));
                    break;
                case "publicationDate":
                    query.select(root).orderBy(builder.desc(root.get(Book_.publicationDate)));
                    break;
                default:
                    break;
            }
        }

        return  HibernateHelper.getResults(em, query);
    }


    /*
     * 3. Get a list of books by author name or lastname
     */

    @Override
    public List<Book> getBooksByAuthorNameOrLastname(final String nameOrLastname) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        final Join<Book, Author> joinAuthor = root.join(Book_.authors);

        query.select(root).where(builder.or(builder.equal(joinAuthor.get(Author_.name), nameOrLastname),
                                            builder.equal(joinAuthor.get(Author_.firstSurname), nameOrLastname),
                                            builder.equal(joinAuthor.get(Author_.secondSurname), nameOrLastname)));

        return  HibernateHelper.getResults(em, query);
    }

    /*
     * 4. Get a list of books by minPrice & maxPrice
     */

    @Override
    public List<Book> getBooksByMinAndMaxPrice(final int minPrice, final int maxPrice) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);


        query.where(builder.and(builder.between(root.get(Book_.price), minPrice, maxPrice),
                                builder.equal(root.get(Book_.deleted), false)));

        return  HibernateHelper.getResults(em, query);
    }

    /*
     * 5. Get a list of books by number of authors
     */

    @Override
    public List<Book> getBooksByXAuthorsNumber(final int authorsNumber) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        final Join<Book, Author> joinAuthor = root.join(Book_.authors);

        query.select(root).groupBy(root).where(builder.equal(builder.countDistinct(joinAuthor), authorsNumber));

        return HibernateHelper.getResults(em, query);
    }

    /*
     * 6. Get a list of books by initialDate & finalDate
     */

    @Override
    public List<Book> getBooksByInitAndFinalPublicationDate(final Date initialDate, final Date finalDate) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        query.where(builder.and(builder.between(root.get(Book_.publicationDate), initialDate, finalDate),
                                builder.equal(root.get(Book_.deleted), false)));

        return HibernateHelper.getResults(em, query);
    }

    /*
     * 7. Get a list of numbers of books for x category
     */

    @Override
    public int countBooksByXCategory(final String category) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Long> counter = builder.createQuery(Long.class);
        final Root<Book> root = counter.from(Book.class);

        counter.select(builder.countDistinct(root.get(Book_.title)))
                .where(builder.and(builder.equal(root.get(Book_.category), category),
                                    builder.equal(root.get(Book_.deleted), false)));

        return HibernateHelper.getSingleResult(em, counter).intValue();
    }

    /*
     * 8. Get a list of books by category
     */

    @Override
    public List<Book> getBooksByCategory(final String category) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        query.where(builder.equal(root.get(Book_.category), category));

        return HibernateHelper.getResults(em, query);
    }

    /*
     * X. AVG of the ranking
     */

}