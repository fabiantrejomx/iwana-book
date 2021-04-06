package mx.dev.blank.dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Book;
import mx.dev.blank.entity.Book_;
import mx.dev.blank.web.controller.request.BookFilterRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    public List<Book> getBooksByMinAndMaxPrice(final BookFilterRequest request) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);
        em.persist(request.getMinPrice());
        em.persist(request.getMaxPrice());

        if (request.getPrice() != 0) {
            query.where(builder.between(root.get(Book_.price), request.getMinPrice(), request.getMaxPrice()));
        }

        return HibernateHelper.getResults(em, query);
    }

    @Override
    public List<Book> getBooksByInitAndFinalPublicationDate(final BookFilterRequest request) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);
        em.persist(request.getInitialPublicationDate());
        em.persist(request.getFinalPublicationDate());

        if (request.getPublicationDate() != null) {
            query.where(builder.between(root.get(Book_.publicationDate), request.getInitialPublicationDate(),
                    request.getFinalPublicationDate()));
        }

        return HibernateHelper.getResults(em, query);
    }

}