package mx.dev.blank.web.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import mx.dev.blank.entity.Category;
import mx.dev.blank.entity.Editor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;


@Data
public class BookForm  implements Serializable {

   private int id;
   @Length(max = 20)
    private String title;

    private int numPages;

    private Category categoryID;

    private Editor editorID;

    private int isbn;
    @Length(max = 1)
    private Integer price;

    @Length(max = 255)
    private String resume;

    @NotBlank
    private Date datePublished;

}
