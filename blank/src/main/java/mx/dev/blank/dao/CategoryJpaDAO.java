package mx.dev.blank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryJpaDAO implements CategoryDAO {

  @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
  private EntityManager em;

  /* create */
  @Override
  public void create(final Category category) {
    em.persist(category);
  }

  /* update */
  @Override
  public void update(final Category category) {
    em.merge(category);
  }

  /* Delete */
  @Override
  public void delete(final Category category) {
    em.remove(category);
  }

  /* Select * FROM category WHERE id = ? */
  @Override
  public Category findById(final int id) {
    return em.find(Category.class, id);
  }
}
