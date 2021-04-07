package mx.dev.blank.web.controller;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.entity.Book;
import mx.dev.blank.service.BookService;
import mx.dev.blank.web.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookRestController {

    @Autowired
    BookService bookService;

    /*@GetMapping(value="/list")
    public ResponseEntity<List<Book>> getBooks(){

        List<Book> books= bookService.getBooks();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);

    }*/

    @GetMapping(value="/list/ordered/asc")
    public ResponseEntity<List<Book>> getOrderAscBooks(@RequestParam Integer limit, @RequestParam(defaultValue ="0") int offset){
        List<Book> books= bookService.getOrderedBooks("asc", limit, offset);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }
    @GetMapping(value="/list/ordered/desc")
    public ResponseEntity<List<Book>> getOrderDescBooks(@RequestParam Integer limit, @RequestParam(defaultValue ="0") int offset){
        List<Book> books= bookService.getOrderedBooks("desc", limit, offset);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping(value="/list/ordered/pages/asc")
    public ResponseEntity<List<Book>> getOrderAscBooksByPages(@RequestParam Integer limit, @RequestParam(defaultValue ="0") int offset){
        List<Book> books= bookService.getOrderedBooksByPages("asc", limit, offset);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping(value="/list/ordered/pages/desc")
    public ResponseEntity<List<Book>> getOrderDescBooksByPages(@RequestParam Integer limit, @RequestParam(defaultValue ="0") int offset){
        List<Book> books= bookService.getOrderedBooksByPages("desc", limit, offset);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping(value="/list/author")
    public ResponseEntity<List<String>> getBooksByAuthor(@RequestParam String author){
        List<String> books= bookService.getBooksByAuthor(author);
        return new ResponseEntity<List<String>>(books, HttpStatus.OK);
    }

    @GetMapping(value="/list/price")
    public ResponseEntity<List<String>> getBooksByPrice(@RequestParam float priceMin, @RequestParam float priceMax){
        List<String> books= bookService.getBooksByPrice(priceMin, priceMax);
        return new ResponseEntity<List<String>>(books, HttpStatus.OK);
    }

    @GetMapping(value="/list/datePublication")
    public ResponseEntity<List<String>> getBooksByDatePublication(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {


        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date start = date.parse(startDate);

        DateFormat dateTwo = new SimpleDateFormat("yyyy-MM-dd");
        Date end = dateTwo.parse(endDate);

        List<String> books= bookService.getBooksByDate(start, end);
        return new ResponseEntity<List<String>>(books, HttpStatus.OK);
    }

    @GetMapping(value="/list/category")
    public ResponseEntity<List<String>> getBooksByCategory(@RequestParam String category){
        List<String> books= bookService.getBooksByCategory(category);
        return new ResponseEntity<List<String>>(books, HttpStatus.OK);
    }

    @GetMapping(value="/list/amountByCategory")
    public ResponseEntity<Long> getAmountOfBooksByCategory(@RequestParam String category){
        Long books= bookService.getAmountOfBooksByCategory(category);
        return new ResponseEntity<Long>(books, HttpStatus.OK);
    }

    @GetMapping(value="/list/amountAuthors")
    public ResponseEntity<List<Book>> getBooksByAmountAuthors(@RequestParam long authors){
        List<Book> books= bookService.getBooksByAmountAuthors(authors);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }


    @PostMapping(path = "/create")
    public ResponseEntity<Book> createBook(
        @RequestBody final BookRequest bookRequest
    ){
        System.out.println(bookRequest);
        Book book = bookService.createBook(bookRequest);

        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }


    @PutMapping(path = "/update/{bookId}")
    public ResponseEntity<Integer> updateBook(
            @PathVariable final Integer bookId,
            @RequestBody final BookRequest bookRequest
    ){
        System.out.println(bookRequest);
        System.out.println(bookId);
        Integer isUpdated = bookService.updateBook(bookId, bookRequest);

        return new ResponseEntity<Integer>(isUpdated, HttpStatus.OK);
    }


}
