package mx.dev.blank.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ISBNValidator.class)
@NotNull
@Size(max = 17, min = 17)
public @interface ISBN {
  String message() default "Incorrect ISBN";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
