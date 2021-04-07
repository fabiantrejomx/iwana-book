package mx.dev.blank.web.controller.response;

import lombok.Getter;
import lombok.Setter;
import mx.dev.blank.entity.Book;

import java.util.List;

@Setter
@Getter
public class BookResponse {

    private List<Book> books;
}
