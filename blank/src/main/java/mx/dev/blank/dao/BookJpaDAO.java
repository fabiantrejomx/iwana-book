package mx.dev.blank.dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}