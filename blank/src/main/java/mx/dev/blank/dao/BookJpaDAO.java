package mx.dev.blank.dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Book;
import mx.dev.blank.entity.Book_;
import mx.dev.blank.entity.Category;
import mx.dev.blank.entity.Category_;
import mx.dev.blank.web.form.BookForm;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookJpaDAO implements BookDAO {
    @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
    private EntityManager em;

    @Override
    public Book findById(Integer bookID) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        query.where(builder.equal(root.get(Book_.id), bookID));

        return em.createQuery(query).getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);

        final Path<Integer> path = root.get(Book_.id);

        query.select(root);

        final TypedQuery<Book> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Book> getAllOrderAsc(final String order, final @Min(1)Integer limit, final @Max(0) Integer offset) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);
        query.select(root);

        if("asc".equals(order)) {
            query.orderBy(builder.asc(root.get(Book_.datePublished)));
        }
        final TypedQuery<Book> typedQuery = em.createQuery(query);
        if(offset!=null){
            typedQuery.setFirstResult(offset);
        }
        if(limit !=null){
            typedQuery.setFirstResult(limit);
        }
        return typedQuery.getResultList();
    }

    @Override
    public List<Book> getAllOrderDesc(final String order, final @Min(1)Integer limit, final @Max(0) Integer offset) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = builder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);
        query.select(root);

        if("desc".equals(order)) {
            query.orderBy(builder.desc(root.get(Book_.datePublished)));
        }
        final TypedQuery<Book> typedQuery = em.createQuery(query);
        if(offset!=null){
            typedQuery.setFirstResult(offset);
        }
        if(limit !=null){
            typedQuery.setFirstResult(limit);
        }
        return typedQuery.getResultList();
    }

    @Override
    public Long getBookByCategory(String categoryName) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Long> query = builder.createQuery(Long.class);
        final Root<Book> root = query.from(Book.class);
        final Join<Book,Category>categoryJoin=root.join(String.valueOf(Category_.id));
        query.multiselect(
                categoryJoin.get(Category_.name),
                builder.count(root.get(Book_.categoryID)));
        query.where(builder.equal(categoryJoin.get(Category_.name),categoryName));

        return em.createQuery(query).getSingleResult();
    }

    @Override
    public List<String> getBookByAuthor(String author) {
        return null;
    }

    @Override
    public Book createBook(BookForm form) {
        Book book=new Book(form.getId(),
                form.getTitle(),
                form.getNumPages(),
                form.getCategoryID(),
                form.getEditorID(),
                form.getIsbn(),
                form.getPrice(),
                form.getResume(),
                form.getDatePublished());
        em.persist(book);
        return book;
    }

    @Override
    public void updatedBook(Book book) {
        em.merge(book);
    }
}
