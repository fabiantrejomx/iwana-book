package mx.dev.blank.validators;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.flywaydb.core.internal.util.StringUtils;

public class ISBNValidator implements ConstraintValidator<ISBN, String> {

  private static final List<String> VALID_PREFIX = Arrays.asList("978", "979");

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {

    final String[] isbnParts = value.split("-");
    if (isbnParts.length != 5) {
      return false;
    }

    final String prefix = isbnParts[0];
    final String code = isbnParts[1];
    final String editorial = isbnParts[2];
    final String format = isbnParts[3];
    final String controlNumber = isbnParts[4];

    if (!StringUtils.isNumeric(prefix)
        || !StringUtils.isNumeric(code)
        || !StringUtils.isNumeric(editorial)
        || !StringUtils.isNumeric(format)
        || !StringUtils.isNumeric(controlNumber)) {
      return false;
    }

    if (!VALID_PREFIX.contains(prefix)) {
      return false;
    }

    if (code.length() > 5 || editorial.length() > 7 || format.length() > 6) {
      return false;
    }

    if (controlNumber.length() != 1 || !StringUtils.isNumeric(controlNumber)) {
      return false;
    }

    return true;
  }
}
