package mx.dev.blank.dao;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import mx.dev.blank.entity.Libro;
import org.springframework.validation.annotation.Validated;

@Validated
public interface LibroDAO {

    List<Libro> ascendente(@Min(0) int currentPos, @Min(1) int size);

    List<Libro> descendente(@Min(0) int currentPos, @Min(1) int size);
}
