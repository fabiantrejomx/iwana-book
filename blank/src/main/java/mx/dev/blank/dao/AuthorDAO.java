package mx.dev.blank.dao;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import mx.dev.blank.entity.Author;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AuthorDAO {

  void create(@NotNull Author author);

  void update(@NotNull Author author);

  void delete(@NotNull Author author);

  Author findById(@Min(1) int id);
}
