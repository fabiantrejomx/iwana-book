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
    public void create(final Book book) {
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
    public List<Book> getBooks(final BookFilterRequest request) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        if(request != null && request.getIsbn() != 0) {
            query.where(builder.equal(root.get(Book_.isbn), request.getIsbn()));
        }

        return HibernateHelper.getResults(em, query);
    }
}