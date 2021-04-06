package mx.dev.blank.web.controller;
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
import mx.dev.blank.model.BookFilterDTO;
import mx.dev.blank.service.BookService;
import mx.dev.blank.web.controller.request.BookFilterRequest;
import mx.dev.blank.web.controller.request.BookRequest;
import mx.dev.blank.web.controller.response.BookResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
@Slf4j
public class BookRestController {

    private final BookService bookService;

    //Add a Book

    @PutMapping
    public ResponseEntity<String> addBook(
            @Valid
            @RequestBody final BookRequest request,
            final BindingResult errors) {

        if (errors.hasErrors()) {
            log.warn("Errors: {}", errors);
            return ResponseEntity.badRequest().body("Incorrect inputs");
        }

        bookService.addBook(request);
        return ResponseEntity.ok("Book added");
    }

    //Update a Book

    @PostMapping(path = "/{bookId}")
    public ResponseEntity<String> updateBook(
            @PathVariable("bookId") final long bookId,
            @Valid
            @RequestBody final BookRequest request,
            final BindingResult errors) {

        if (errors.hasErrors()) {
            log.warn("Errors: {}", errors);
            return ResponseEntity.badRequest().body("Incorrect inputs");
        }

        try {
            bookService.editBook(bookId, request);
            return ResponseEntity.ok("Book updated");
        } catch(final ResourceNotFound ex) {
            log.warn("Incorrect bookId: {}", bookId);
            return ResponseEntity.badRequest().body("Incorrect bookId");
        }
    }

    //Delete a Book

    @DeleteMapping(path = "/{bookId}")
    public ResponseEntity<String> deleteBook(
            @PathVariable("bookId") final long bookId) {

        try {
            bookService.deleteBook(bookId);
            return ResponseEntity.ok("Book deleted");
        } catch(final ResourceNotFound ex) {
            log.warn("Incorrect bookId: {}", bookId);
            return ResponseEntity.badRequest().body("Incorrect bookId");
        }
    }

    private Book build(final Book book) {
        return new Book(book.getTitle(), book.getPagesNumber(), book.getAuthors(), book.getEditorial(), book.getIsbn(),
                book.getCategory(), book.getPrice(), book.getSummary(), book.getPublicationDate());
    }

    //Search Filters

    @InitBinder("BookFilter")
    private void initBinderRouteCoverageFilterRequestByPublicationDate(final WebDataBinder binder) {
        binder.setAllowedFields("publicationDate");
        binder.setAllowedFields("price");
        binder.setAllowedFields("category");
        binder.setAllowedFields("title");
        binder.setAllowedFields("pagesNumber");
    }

    /*
     * 1. Get a list of courses by publicationDate order by asc
     */
    @GetMapping(path = "/filter")
    public ResponseEntity<BookResponse> getBookByPublicationDateFilterOrderByAsc(@ModelAttribute("BookFilter")
                                                            final BookFilterRequest filters){
        log.info("Request {}", filters);
        final BookResponse response = new BookResponse();
        final List<Book> courses = bookService.getBooksListByPublicationDateOrderByAsc(filters);
        response.setBooks(courses.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 2. Get a list of books by publicationDate order by desc
     */
    @GetMapping(path = "/filter")
    public ResponseEntity<BookResponse> getBookByPublicationDateFilterOrderByDesc(@ModelAttribute("BookFilter")
                                                                               final BookFilterRequest filters){
        log.info("Request {}", filters);
        final BookResponse response = new BookResponse();
        final List<Book> courses = bookService.getBooksListByPublicationDateOrderByDesc(filters);
        response.setBooks(courses.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 3. Get a list of books by author name or lastname
     */

    @GetMapping(path = "/filter")
    public ResponseEntity<BookResponse> getBooksByAuthorNameOrLastname(@ModelAttribute("BookFilter")
                                                          final BookFilterRequest filters) {

        log.info("Request {}", filters);
        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByAuthorNameOrLastname(filters);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 4. Get a list of books by minPrice & maxPrice
     */

    @GetMapping(path = "/filter")
    public ResponseEntity<BookResponse> getBookByMinAndMaxPriceFilter(@ModelAttribute("BookFilter")
                                                                    final BookFilterRequest filters) {
        log.info("Request {}", filters);
        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByMinAndMaxPrice(filters);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 5. Get a list of books by number of authors
     */

    /*
     * 6. Get a list of books by initialDate & finalDate
     */

    @GetMapping(path = "/filter")
    public ResponseEntity<BookResponse> getBookByInitAndFinalPublicationDateFilter(@ModelAttribute("BookFilter")
                                                                                final BookFilterRequest filters) {
        log.info("Request {}", filters);
        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByInitAndFinalPublicationDate(filters);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 7. Get a numbers of Books of X Category
     */

    @GetMapping(path = "/{category}")
    public @ResponseBody int countBooksByXCategory(@PathVariable("category") final String category){

        try {
            return (bookService.countBooksByXCategory(category));
        } catch(final ResourceNotFound ex) {
            log.warn("Incorrect category: {}", category);
            return 0;
        }
    }

    /*
     * 8. Get a list of books by category
     */

    @GetMapping(path = "/filter")
    public ResponseEntity<BookResponse> getBookByCategory(@ModelAttribute("BookFilter")
                                                           final BookFilterRequest filters) {

        log.info("Request {}", filters);
        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByCategory(filters);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 9. Get a list of books by pageNumber By Asc and Desc
     */

    @GetMapping(path = "/filter")
    public ResponseEntity<BookResponse> getBookByPagesNumberFilterOrderByAsc(@ModelAttribute("BookFilter")
                                                                                 final BookFilterRequest filters){
        log.info("Request {}", filters);
        final BookResponse response = new BookResponse();
        final List<Book> courses = bookService.getBooksListByPagesNumberOrderByAsc(filters);
        response.setBooks(courses.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<BookResponse> getBookByPagesNumberFilterOrderByDesc(@ModelAttribute("BookFilter")
                                                                             final BookFilterRequest filters){
        log.info("Request {}", filters);
        final BookResponse response = new BookResponse();
        final List<Book> courses = bookService.getBooksListByPagesNumberOrderByDesc(filters);
        response.setBooks(courses.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }


}
