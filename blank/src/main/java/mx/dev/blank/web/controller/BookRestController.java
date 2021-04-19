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
import mx.dev.blank.web.request.BookSearchForm;
import mx.dev.blank.web.response.BaseResponse;
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

  /*
   * Ejercicio 1, 2, 9
   */
  @GetMapping
  public ResponseEntity<List<Book>> getBooks(@ModelAttribute BookSearchForm form) {

    final List<Book> books = bookService.getBooks(form);

    return ResponseEntity.ok(books);
  }

  /*
   * Ejercicio 3
   */
  @GetMapping(value = "/author")
  public ResponseEntity<List<Book>> getBooksByAuthor(
      @RequestParam(value = "author") String author) {
    final List<Book> books = bookService.getBooksByAuthor(author);
    return ResponseEntity.ok(books);
  }

  /*
   * Ejercicio 4
   */
  @GetMapping(value = "/price")
  public ResponseEntity<List<Book>> getBooksByPrice(
      @RequestParam BigDecimal priceMin, @RequestParam BigDecimal priceMax) {
    final List<Book> books = bookService.getBooksByPrice(priceMin, priceMax);
    return ResponseEntity.ok(books);
  }

  /*
   * Ejercicio 5
   */
  @GetMapping(value = "/authors")
  public ResponseEntity<List<Book>> getBooksByAmountAuthors(@RequestParam long authors) {
    final List<Book> books = bookService.getBooksByAmountAuthors(authors);
    return ResponseEntity.ok(books);
  }

  /*
   * Ejercicio 6
   */
  @GetMapping(value = "/datePublication")
  public ResponseEntity<List<Book>> getBooksByDatePublication(
      @RequestParam String startDate, @RequestParam String endDate) throws ParseException {

    final DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    final Date start = date.parse(startDate);
    final Date end = date.parse(endDate);

    final List<Book> books = bookService.getBooksByDate(start, end);
    return ResponseEntity.ok(books);
  }

  /*
   * Ejercicio 7
   */
  @GetMapping(value = "/amountByCategory")
  public ResponseEntity<Long> getAmountOfBooksByCategory(@RequestParam String category) {
    final Long books = bookService.getAmountOfBooksByCategory(category);
    return ResponseEntity.ok(books);
  }

  /*
   * Ejercicio 8
   */
  @GetMapping(value = "/category")
  public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam String category) {
    final List<Book> books = bookService.getBooksByCategory(category);
    return ResponseEntity.ok(books);
  }

  //  @GetMapping(value = "/list/ordered/ranking")
  //  public ResponseEntity<List<BookWithRanking>> getBooksWithScore(
  //      @RequestParam Integer limit, @RequestParam(defaultValue = "0") int offset) {
  //    List<BookWithRanking> books = bookService.getBooksWithScore("desc", limit, offset);
  //    return new ResponseEntity<List<BookWithRanking>>(books, HttpStatus.OK);
  //  }
}
