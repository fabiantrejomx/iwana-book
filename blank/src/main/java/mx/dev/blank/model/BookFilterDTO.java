package mx.dev.blank.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@RequiredArgsConstructor
@Getter
public class BookFilterDTO {

    private final long id;
    private final String title;
    private final int pagesNumber;
    private final String authors;
    private final String editorial;
    private final int isbn;
    private final String category;
    private final int price;
    private final String summary;
    private final Date publicationDate;

}
