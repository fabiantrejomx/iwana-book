package mx.dev.blank.web.response;

import lombok.Getter;
import lombok.Setter;
import mx.dev.blank.entity.Book;

@Getter
@Setter
public class BookWithScore extends Book{
    private Double score;
}
