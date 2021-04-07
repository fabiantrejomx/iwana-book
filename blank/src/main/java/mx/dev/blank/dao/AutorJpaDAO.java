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
import mx.dev.blank.entity.Autor;
import mx.dev.blank.entity.Autor_;

@RequiredArgsConstructor
public class AutorJpaDAO implements AutorDAO{

    @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
    private EntityManager em;

    /**
     * SELECT * FROM Libros WHERE id = '${id}'
     */
    @Override
    public Autor getById(final int id) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Autor> query = builder.createQuery(Autor.class);
        final Root<Autor> root = query.from(Autor.class);

        final Path<Integer> path = root.get(Autor_.id);
        final Predicate predicate = builder.equal(path, id);

        query.select(root).where(predicate);

        final TypedQuery<Autor> typedQuery = em.createQuery(query);
        return typedQuery.getSingleResult();
    }
}
