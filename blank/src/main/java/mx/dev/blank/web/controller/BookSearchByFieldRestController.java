package mx.dev.blank.web.controller;
import java.util.Collections;
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
public class BookSearchByFieldRestController {

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

    @GetMapping(path = "/{courseId}")
    public @ResponseBody Book getBook(@PathVariable("courseId") final long bookId){

        try {
            return build(bookService.findBook(bookId));
        } catch(final ResourceNotFound ex) {
            log.warn("Incorrect courseId: {}", bookId);
            return null;
        }
    }


    /*
     * 1. Get a list of courses by publicationDate order by asc
     * 2. Get a list of books by publicationDate order by desc
     * 9. Get a list of books by pageNumber By Asc and Desc
     */


    @GetMapping(path = "/order_by_any_filter_asc")
    public ResponseEntity<BookResponse> getBooksByAnyFieldOrderByAsc(@ModelAttribute("BookFilter")
                                                                    final BookRequest fields, final String orderField){
        log.info("Request {}", fields);

        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByAnyFieldAsc(fields, orderField);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/order_by_any_filter_desc")
    public ResponseEntity<BookResponse> getBooksByAnyFieldOrderByDesc(@ModelAttribute("BookFilter")
                                                                     final BookRequest fields, final String orderField){
        log.info("Request {}", fields);
        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByAnyFieldDesc(fields, orderField);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     * 8. Get a list of books by category
     */

    @GetMapping(path = "/book_category")
    public ResponseEntity<BookResponse> getBookByCategory(@ModelAttribute("BookFilter")
                                                           final String category) {

        log.info("Request {}", category);
        final BookResponse response = new BookResponse();
        final List<Book> books = bookService.getBooksListByCategory(category);
        response.setBooks(books.stream().map(this::build).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

}
