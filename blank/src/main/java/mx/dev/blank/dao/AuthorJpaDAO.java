package mx.dev.blank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mx.dev.blank.entity.Author;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthorJpaDAO implements AuthorDAO {

  @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
  private EntityManager em;

  /* create */
  @Override
  public void create(final Author author) {
    em.persist(author);
  }

  /* update */
  @Override
  public void update(final Author author) {
    em.merge(author);
  }

  /* Delete */
  @Override
  public void delete(final Author author) {
    em.remove(author);
  }

  /* Select * FROM author WHERE id = ? */
  @Override
  public Author findById(final int id) {
    return em.find(Author.class, id);
  }
}
