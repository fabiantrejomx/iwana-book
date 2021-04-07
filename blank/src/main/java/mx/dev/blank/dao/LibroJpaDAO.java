package mx.dev.blank.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Libro;
import mx.dev.blank.entity.Libro_;

@RequiredArgsConstructor
public class LibroJpaDAO implements LibroDAO {

    @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
    private EntityManager em;

    /**
     * SELECT * FROM Libros WHERE id = '${id}'
     */
    @Override
    public Libro getById(final int id) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Libro> query = builder.createQuery(Libro.class);
        final Root<Libro> root = query.from(Libro.class);

        final Path<Integer> path = root.get(Libro_.id);
        final Predicate predicate = builder.equal(path, id);

        query.select(root).where(predicate);

        final TypedQuery<Libro> typedQuery = em.createQuery(query);
        return typedQuery.getSingleResult();
    }
}
