package mx.dev.blank.web.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.dev.blank.validators.BookValidator;
import mx.dev.blank.validators.ISBN;

@Getter
@ToString
@NoArgsConstructor
@BookValidator
public class BookRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  @NotNull
  @Size(max = 255)
  private String title;

  @Min(1)
  private int pages;

  @ISBN private String isbn;

  @NotNull
  @Min(0)
  @Max(999999)
  private BigDecimal price;

  @NotNull
  @Size(max = 350)
  private String summary;

  @NotNull
  @Size(max = 35)
  private String editorial;

  @NotNull
  @Temporal(TemporalType.DATE)
  private Date datePublication;

  @NotEmpty private Set<Integer> categories;

  @NotEmpty private Set<Integer> authors;
}
