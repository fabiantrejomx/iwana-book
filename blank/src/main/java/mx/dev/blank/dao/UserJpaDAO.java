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
import mx.dev.blank.entity.User;
import mx.dev.blank.entity.User_;

@RequiredArgsConstructor
public class UserJpaDAO implements UserDAO {

  @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
  private EntityManager em;

  /**
   * SELECT * FROM user WHERE id = '${id}'
   */
  @Override
  public User getById(final String id) {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<User> query = builder.createQuery(User.class);
    final Root<User> root = query.from(User.class);

    final Path<String> path = root.get(User_.id);
    final Predicate predicate = builder.equal(path, id);

    query.select(root).where(predicate);

    final TypedQuery<User> typedQuery = em.createQuery(query);
    return typedQuery.getSingleResult();
  }

}
