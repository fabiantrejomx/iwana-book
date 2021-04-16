package mx.dev.blank.dao;

import javax.validation.constraints.NotBlank;
import mx.dev.blank.entity.User;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserDAO {

  User getById(@NotBlank String id);
}
