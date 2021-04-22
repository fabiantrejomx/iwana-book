package mx.dev.blank.validators;

import java.time.LocalDate;
import java.time.ZoneId;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import mx.dev.blank.web.request.BookRequest;

public class BookAnnotationValidator implements ConstraintValidator<BookValidator, BookRequest> {

  @Override
  public boolean isValid(final BookRequest form, final ConstraintValidatorContext context) {

    if (form.getDatePublication() != null) {
      final LocalDate startOfCentury = LocalDate.of(1900, 1, 1);
      final LocalDate publishDate =
          form.getDatePublication().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

      if (publishDate.isBefore(startOfCentury)
          && !form.getEditorial().equalsIgnoreCase("Desconocido")) {
        return false;
      }
    }
    return true;
  }
}
