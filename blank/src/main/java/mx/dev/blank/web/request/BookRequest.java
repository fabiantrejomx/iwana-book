package mx.dev.blank.web.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.dev.blank.entity.Category;

@EqualsAndHashCode(of = {"id"})
@Getter
@ToString
@NoArgsConstructor()
public class BookRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  private String title;
  private int pages;
  private String isbn;
  private BigDecimal price;
  private String summary;
  private String editorial;

  @Temporal(TemporalType.DATE)
  private Date datePublication;

  private Category category;
}
