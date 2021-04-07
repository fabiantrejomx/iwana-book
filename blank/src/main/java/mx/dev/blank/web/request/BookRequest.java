package mx.dev.blank.web.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.dev.blank.entity.Category;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(of = {"id"})
@Getter
@ToString
@NoArgsConstructor()
public class BookRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private int pages;
    private String isbn;
    private float price ;
    private String summary;
    private String editorial;
    @Temporal(TemporalType.DATE)
    private Date datePublication;
    private Category category;
}
