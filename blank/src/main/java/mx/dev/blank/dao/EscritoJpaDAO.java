package mx.dev.blank.dao;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Autor;
import mx.dev.blank.entity.Autor_;
import mx.dev.blank.entity.Escrito;
import mx.dev.blank.entity.Escrito_;
import mx.dev.blank.entity.Libro;
import mx.dev.blank.entity.Libro_;

@RequiredArgsConstructor
public class EscritoJpaDAO implements EscritoDAO {

    @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
    private EntityManager em;

    @Override
    public List<Escrito> xAutor(final String campo) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Escrito> query = builder.createQuery(Escrito.class);
        final Root<Escrito> root = query.from(Escrito.class);

        final Join<Escrito, Autor> joinAutor = root.join(Escrito_.autor);
        final Join<Escrito, Libro> joinLibro = root.join(Escrito_.libro);

        query.multiselect(
                joinAutor.get(Autor_.id),
                joinAutor.get(Autor_.nombre),
                joinAutor.get(Autor_.apep),
                joinAutor.get(Autor_.apem),
                joinLibro.get(Libro_.id),
                joinLibro.get(Libro_.titulo)
        );

        query.where(builder.equal(joinAutor.get(Autor_.id), campo));

        return em.createQuery(query).getResultList();
    }
}
