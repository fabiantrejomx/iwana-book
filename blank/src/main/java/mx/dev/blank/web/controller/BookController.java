package mx.dev.blank.web.controller;

import lombok.Lombok;
import mx.dev.blank.entity.Book;
import mx.dev.blank.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping(value = "/list/books")
    public ResponseEntity<List<Book>> getbooks(){
        List<Book>books=bookService.getAll();
        return ResponseEntity.ok(books);
    }
}
