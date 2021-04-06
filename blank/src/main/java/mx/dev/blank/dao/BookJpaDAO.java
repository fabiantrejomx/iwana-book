package mx.dev.blank.dao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Author;
import mx.dev.blank.entity.Author_;
import mx.dev.blank.entity.Book;
import mx.dev.blank.entity.Book_;
import mx.dev.blank.web.controller.request.BookFilterRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class BookJpaDAO implements BookDAO {

    @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
    private EntityManager em;

    /**
     * INSERT INTO book (title, page_number,...) VALUES (${title}, ${page_number},...)
     */

    @Override
    public void add(final Book book) {
        em.persist(book);
    }

    /**
     * UPDATE books SET title=${title},page_number=${page_number,...} WHERE id = ${id}
     */

    @Override
    public void update(final Book book) {
        em.merge(book);
    }

    /**
     * DELETE FROM book WHERE id = ${id}
     */

    @Override
    public void delete(final Book book) {
        em.remove(book);
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
     * 1. Get a list of courses by publicationDate order by asc
     */

    @Override
    public List<Book> getBooksByPublicationDateAsc(final BookFilterRequest request) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        if (request != null && request.getPublicationDate() != null) {
            query.orderBy(builder.asc(root.get(Book_.publicationDate)));
        }

        return HibernateHelper.getResults(em, query);
    }

    /*
     * 2. Get a list of books by publicationDate order by desc
     */

    @Override
    public List<Book> getBooksByPublicationDateDesc(final BookFilterRequest request) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        if (request != null && request.getPublicationDate() != null) {
            query.orderBy(builder.desc(root.get(Book_.publicationDate)));
        }

        return HibernateHelper.getResults(em, query);
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

        query.multiselect(
                root.get(Book_.title),
                root.get(Book_.editorial),
                root.get(Book_.publicationDate));

        query.where(builder.or(builder.equal(joinAuthor.get(Author_.name), nameOrLastname),
                builder.equal(joinAuthor.get(Author_.firstSurname), nameOrLastname),
                builder.equal(joinAuthor.get(Author_.secondSurname), nameOrLastname)));

        return em.createQuery(query).getResultList();
    }

    /*
     * 4. Get a list of books by minPrice & maxPrice
     */

    @Override
    public List<Book> getBooksByMinAndMaxPrice(final int minPrice, final int maxPrice) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);


        query.where(builder.between(root.get(Book_.price), minPrice, maxPrice));

        return HibernateHelper.getResults(em, query);
    }

    /*
     * 5. Get a list of books by number of authors
     */



    /*
     * 6. Get a list of books by initialDate & finalDate
     */

    @Override
    public List<Book> getBooksByInitAndFinalPublicationDate(final Date initialDate, final Date finalDate) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        query.where(builder.between(root.get(Book_.publicationDate), initialDate, finalDate));

        return HibernateHelper.getResults(em, query);
    }

    /*
     * 7. Get a list of numbers of books for x category
     */

    @Override
    public int countBooksByXCategory(final String category) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Long> query = builder.createQuery(Long.class);
        final Root<Book> root = query.from(Book.class);

        query
                .select(builder.countDistinct(root.get(Book_.title)))
                .where(builder.equal(root.get(Book_.category), category));

        return HibernateHelper.getSingleResult(em, query).intValue();
    }

    /*
     * 8. Get a list of books by category
     */

    @Override
    public List<Book> getBooksByCategory(final BookFilterRequest request) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        if (request != null && request.getCategory() != null) {
            query.where(builder.equal(root.get(Book_.category), request.getCategory()));
        }

        return HibernateHelper.getResults(em, query);
    }

    /*
     * 9. Get a list of books by pageNumber By Asc and Desc
     */

    @Override
    public List<Book> getBooksByPageNumberAsc(final BookFilterRequest request) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        if (request != null && request.getPagesNumber() != 0) {
            query.orderBy(builder.asc(root.get(Book_.pagesNumber)));
        }

        return HibernateHelper.getResults(em, query);
    }

    @Override
    public List<Book> getBooksByPageNumberDesc(final BookFilterRequest request) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        if (request != null && request.getPagesNumber() != 0) {
            query.orderBy(builder.desc(root.get(Book_.pagesNumber)));
        }

        return HibernateHelper.getResults(em, query);
    }


}