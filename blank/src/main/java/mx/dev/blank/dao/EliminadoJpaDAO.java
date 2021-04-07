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
import mx.dev.blank.entity.Eliminado;
import mx.dev.blank.entity.Eliminado_;

@RequiredArgsConstructor
public class EliminadoJpaDAO implements EliminadoDAO{

    @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
    private EntityManager em;

    /**
     * SELECT * FROM Eliminadoss WHERE id = '${id}'
     */
    @Override
    public Eliminado getById(final int id) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Eliminado> query = builder.createQuery(Eliminado.class);
        final Root<Eliminado> root = query.from(Eliminado.class);

        final Path<Integer> path = root.get(Eliminado_.id);
        final Predicate predicate = builder.equal(path, id);

        query.select(root).where(predicate);

        final TypedQuery<Eliminado> typedQuery = em.createQuery(query);
        return typedQuery.getSingleResult();
    }
}
