package mx.dev.blank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthorJpaDAO implements AuthorDAO {

  @Setter(onMethod = @__(@PersistenceContext), value = AccessLevel.PACKAGE)
  private EntityManager em;
}
