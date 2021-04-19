package mx.dev.blank.web.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.entity.Book;
import mx.dev.blank.service.BookService;
import mx.dev.blank.web.request.BookRequest;
import mx.dev.blank.web.response.BaseResponse;
import mx.dev.blank.web.response.BookWithRanking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookRestController {

  private final BookService bookService;

  @PostMapping
  public ResponseEntity<BaseResponse> createBook(
      @Valid @RequestBody final BookRequest bookRequest, final BindingResult errors) {

    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(BaseResponse.fail(errors.getAllErrors()));
    }

    bookService.createBook(bookRequest);

    return ResponseEntity.ok(BaseResponse.success("Book created successfully"));
  }

  @PutMapping(path = "/{bookId}")
  public ResponseEntity<BaseResponse> updateBook(
      @PathVariable(name = "bookId") final int bookId,
      @Valid @RequestBody final BookRequest bookRequest,
      final BindingResult errors) {

    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(BaseResponse.fail(errors.getAllErrors()));
    }

    bookService.updateBook(bookId, bookRequest);

    return ResponseEntity.ok(BaseResponse.success("Book updated successfully"));
  }

  @DeleteMapping(path = "/{bookId}")
  public ResponseEntity<BaseResponse> deleteBook(@PathVariable(name = "bookId") final int bookId) {

    bookService.deleteBook(bookId);
    return ResponseEntity.ok(BaseResponse.success("Book deleted successfully"));
  }

  @GetMapping(value = "/list/ordered/asc")
  public ResponseEntity<List<Book>> getOrderAscBooks(
      @RequestParam Integer limit, @RequestParam(defaultValue = "0") int offset) {
    List<Book> books = bookService.getOrderedBooks("asc", limit, offset);
    return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/list/ordered/desc")
  public ResponseEntity<List<Book>> getOrderDescBooks(
      @RequestParam Integer limit, @RequestParam(defaultValue = "0") int offset) {
    List<Book> books = bookService.getOrderedBooks("desc", limit, offset);
    return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/list/ordered/pages/asc")
  public ResponseEntity<List<Book>> getOrderAscBooksByPages(
      @RequestParam Integer limit, @RequestParam(defaultValue = "0") int offset) {
    List<Book> books = bookService.getOrderedBooksByPages("asc", limit, offset);
    return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/list/ordered/pages/desc")
  public ResponseEntity<List<Book>> getOrderDescBooksByPages(
      @RequestParam Integer limit, @RequestParam(defaultValue = "0") int offset) {
    List<Book> books = bookService.getOrderedBooksByPages("desc", limit, offset);
    return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/list/author")
  public ResponseEntity<List<String>> getBooksByAuthor(@RequestParam String author) {
    List<String> books = bookService.getBooksByAuthor(author);
    return new ResponseEntity<List<String>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/list/price")
  public ResponseEntity<List<String>> getBooksByPrice(
      @RequestParam BigDecimal priceMin, @RequestParam BigDecimal priceMax) {
    List<String> books = bookService.getBooksByPrice(priceMin, priceMax);
    return new ResponseEntity<List<String>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/list/datePublication")
  public ResponseEntity<List<String>> getBooksByDatePublication(
      @RequestParam String startDate, @RequestParam String endDate) throws ParseException {

    DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    Date start = date.parse(startDate);

    DateFormat dateTwo = new SimpleDateFormat("yyyy-MM-dd");
    Date end = dateTwo.parse(endDate);

    List<String> books = bookService.getBooksByDate(start, end);
    return new ResponseEntity<List<String>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/list/category")
  public ResponseEntity<List<String>> getBooksByCategory(@RequestParam String category) {
    List<String> books = bookService.getBooksByCategory(category);
    return new ResponseEntity<List<String>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/list/amountByCategory")
  public ResponseEntity<Long> getAmountOfBooksByCategory(@RequestParam String category) {
    Long books = bookService.getAmountOfBooksByCategory(category);
    return new ResponseEntity<Long>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/list/amountAuthors")
  public ResponseEntity<List<Book>> getBooksByAmountAuthors(@RequestParam long authors) {
    List<Book> books = bookService.getBooksByAmountAuthors(authors);
    return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
  }

  @GetMapping(value = "/list/ordered/ranking")
  public ResponseEntity<List<BookWithRanking>> getBooksWithScore(
      @RequestParam Integer limit, @RequestParam(defaultValue = "0") int offset) {
    List<BookWithRanking> books = bookService.getBooksWithScore("desc", limit, offset);
    return new ResponseEntity<List<BookWithRanking>>(books, HttpStatus.OK);
  }
}
