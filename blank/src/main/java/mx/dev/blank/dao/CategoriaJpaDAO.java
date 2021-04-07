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
import mx.dev.blank.entity.Categoria;
import mx.dev.blank.entity.Categoria_;

@RequiredArgsConstructor
public class CategoriaJpaDAO implements CategoriaDAO {

    @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
    private EntityManager em;

    /**
     * SELECT * FROM Libros WHERE id = '${id}'
     */
    @Override
    public Categoria getById(final int id) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);
        final Root<Categoria> root = query.from(Categoria.class);

        final Path<Integer> path = root.get(Categoria_.id);
        final Predicate predicate = builder.equal(path, id);

        query.select(root).where(predicate);

        final TypedQuery<Categoria> typedQuery = em.createQuery(query);
        return typedQuery.getSingleResult();
    }
}
