package mx.dev.blank.web.controller;

import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.entity.Book;
import mx.dev.blank.service.BookService;
import mx.dev.blank.web.form.BookForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> getbooks(){
        List<Book>books=bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/books/asc")
    public ResponseEntity<List<Book>> getbooksOrderAsc(@RequestParam String order,@RequestParam int limit, @RequestParam(defaultValue = "0") int offset){
        List<Book>books=bookService.getAllOrderAsc(order,limit,offset);
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/books/desc")
    public ResponseEntity<List<Book>> getbooksOrderDesc(@RequestParam String order,@RequestParam int limit, @RequestParam(defaultValue = "0") int offset){
        List<Book>books=bookService.getAllOrderDesc(order,limit,offset);
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody final BookForm form){
        System.out.println(form);
        Book book= bookService.createBook(form);
        return  ResponseEntity.ok(book);
    }
}
