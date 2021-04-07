package mx.dev.blank.dao;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import mx.dev.blank.entity.Eliminado;
import org.springframework.validation.annotation.Validated;

@Validated
public interface EliminadoDAO {

    Eliminado getById(@NotBlank int id);
}