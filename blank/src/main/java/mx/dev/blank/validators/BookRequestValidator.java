package mx.dev.blank.validators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import mx.dev.blank.web.request.BookRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookRequestValidator implements Validator {

  private final BigDecimal limitPricePerPage = new BigDecimal(2.5);

  @Override
  public boolean supports(final Class<?> clazz) {
    return BookRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(final Object target, final Errors errors) {
    final BookRequest form = (BookRequest) target;

    if (form.getPrice() != null && form.getPages() > 0) {
      final BigDecimal pricePerPage =
          form.getPrice().divide(new BigDecimal(form.getPages()), 3, RoundingMode.FLOOR);
      if (pricePerPage.compareTo(limitPricePerPage) > 0) {
        errors.rejectValue("price", "Price per Page is too high");
      }
    }
  }
}
