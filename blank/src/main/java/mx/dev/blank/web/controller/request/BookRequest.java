package mx.dev.blank.web.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String authors;
    @NotBlank
    private String editorial;
    @NotBlank
    private int isbn;
    @NotBlank
    private String category;
    @NotBlank
    private int price;
    @NotBlank
    private String summary;
    @NotBlank
    private Date publicationDate;

}
