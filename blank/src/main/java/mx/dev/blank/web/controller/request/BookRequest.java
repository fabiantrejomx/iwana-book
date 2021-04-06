package mx.dev.blank.web.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import mx.dev.blank.entity.Author;
import mx.dev.blank.entity.Category;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class BookRequest {

    @NotBlank
    private String title;
    @NotBlank
    private int pagesNumber;
    @NotBlank
    private Author authors;
    @NotBlank
    private String editorial;
    @NotBlank
    private int isbn;
    @NotBlank
    private Category category;
    @NotBlank
    private int price;
    @NotBlank
    private String summary;
    @NotBlank
    private Date publicationDate;

}
