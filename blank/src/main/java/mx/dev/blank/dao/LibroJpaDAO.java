package mx.dev.blank.dao;

import java.sql.Date;
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


    @Override
    public List<Libro> ascendente(final int currentPos, final int size) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Libro> query = builder.createQuery(Libro.class);
        final Root<Libro> root = query.from(Libro.class);

        final Path<Date> path = root.get(Libro_.publicacion);
        query.select(root).orderBy(builder.asc(path));

        return em.createQuery(query).setFirstResult(currentPos).setMaxResults(size).getResultList();

    }

    @Override
    public List<Libro> descendente(final int currentPos, final int size) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Libro> query = builder.createQuery(Libro.class);
        final Root<Libro> root = query.from(Libro.class);

        final Path<Date> path = root.get(Libro_.publicacion);
        query.select(root).orderBy(builder.desc(path));

        return em.createQuery(query).setFirstResult(currentPos).setMaxResults(size).getResultList();

    }
}
