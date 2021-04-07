package mx.dev.blank.web.controller;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.entity.Book;
import mx.dev.blank.exceptions.ResourceNotFound;
import mx.dev.blank.service.BookService;
import mx.dev.blank.web.controller.request.BookFilterRequest;
import mx.dev.blank.web.controller.request.BookRequest;
import mx.dev.blank.web.controller.response.BookResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
@Slf4j
public class BookSearchRestController {

    private final BookService bookService;

    @InitBinder("BookFilter")
    private void initBinderRouteCoverageFilterRequest(final WebDataBinder binder) {
        binder.setAllowedFields("title");
        binder.setAllowedFields("pagesNumber");
        binder.setAllowedFields("authors");
        binder.setAllowedFields("editorial");
        binder.setAllowedFields("isbn");
        binder.setAllowedFields("category");
        binder.setAllowedFields("price");
        binder.setAllowedFields("summary");
        binder.setAllowedFields("publicationDate");
    }

    private Book build(final Book book) {
        return new Book(book.getTitle(), book.getPagesNumber(), book.getAuthors(), book.getEditorial(), book.getIsbn(),
                book.getCategory(), book.getPrice(), book.getSummary(), book.getPublicationDate());
    }

    /*
     * 3. Get a list of books by author name or lastname
     */

    @GetMapping(path = "/author_name_or_last_name")
    public ResponseEntity<BookResponse> getBooksByAuthorNameOrLastname(@ModelAttribute("BookFilter")
                                                                       final String nameOrLastname) {

        log.info("Request {}", nameOrLastname);
        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByAuthorNameOrLastname(nameOrLastname);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 4. Get a list of books by minPrice & maxPrice
     */

    @GetMapping(path = "/book_min_max_price")
    public ResponseEntity<BookResponse> getBookByMinAndMaxPriceFilter(@ModelAttribute("BookFilter")
                                                                      final int minPrice,
                                                                      final int maxPrice) {
        log.info("Request {}", minPrice, maxPrice);
        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByMinAndMaxPrice(minPrice, maxPrice);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 5. Get a list of books by number of authors
     */

    @GetMapping(path = "/book_x_authors_number")
    public ResponseEntity<BookResponse> getBookByXAuthorsNumber(@ModelAttribute("BookFilter")
                                                                    final int authorsNumber) {
        log.info("Request {}", authorsNumber);
        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByXAuthorsNumber(authorsNumber);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 6. Get a list of books by initialDate & finalDate
     */

    @GetMapping(path = "/book_init_final_date")
    public ResponseEntity<BookResponse> getBookByInitAndFinalPublicationDateFilter(@ModelAttribute("BookFilter")
                                                                                   final Date initialDate,
                                                                                   final Date finalDate) {
        log.info("Request {}", initialDate, finalDate);
        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByInitAndFinalPublicationDate(initialDate, finalDate);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 7. Get a numbers of Books of X Category
     */

    @GetMapping(path = "/{category}")
    public @ResponseBody
    int countBooksByXCategory(@PathVariable("category") final String category){

        try {
            return (bookService.countBooksByXCategory(category));
        } catch(final ResourceNotFound ex) {
            log.warn("Incorrect category: {}", category);
            return 0;
        }
    }
}
