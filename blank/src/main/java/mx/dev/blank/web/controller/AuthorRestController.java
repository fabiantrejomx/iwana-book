package mx.dev.blank.web.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.entity.Author;
import mx.dev.blank.entity.Book;
import mx.dev.blank.entity.Category;
import mx.dev.blank.model.AuthorDTO;
import mx.dev.blank.model.BookDTO;
import mx.dev.blank.service.AuthorService;
import mx.dev.blank.web.request.AuthorRequest;
import mx.dev.blank.web.request.BookSearchForm;
import mx.dev.blank.web.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorRestController {

  private final AuthorService authorService;

  @PostMapping
  public ResponseEntity<BaseResponse> createAuthor(
      @Valid @RequestBody final AuthorRequest authorRequest, final BindingResult errors) {

    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(BaseResponse.fail(errors.getAllErrors()));
    }

    authorService.createAuthor(authorRequest);

    return ResponseEntity.ok(BaseResponse.success("Author created successfully"));
  }

  @PutMapping(path = "/{authorId}")
  public ResponseEntity<BaseResponse> updateAuthor(
      @PathVariable(name = "authorId") final int authorId,
      @Valid @RequestBody final AuthorRequest authorRequest,
      final BindingResult errors) {

    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(BaseResponse.fail(errors.getAllErrors()));
    }

    authorService.updateAuthor(authorId, authorRequest);

    return ResponseEntity.ok(BaseResponse.success("Author updated successfully"));
  }

  @DeleteMapping(path = "/{authorId}")
  public ResponseEntity<BaseResponse> deleteAuthor(
      @PathVariable(name = "authorId") final int authorId) {

    authorService.deleteAuthor(authorId);
    return ResponseEntity.ok(BaseResponse.success("Author deleted successfully"));
  }

  @GetMapping(value = "/list")
  public ResponseEntity<List<Author>> getAuthors() {

    final List<Author> authors = authorService.findAll();
    return ResponseEntity.ok(authors);
  }

  @GetMapping(value = "/{authorId}")
  public ResponseEntity<Author> getByAuthorId(@PathVariable int authorId) {

    final Author author = authorService.getByAuthorId(authorId);
    return ResponseEntity.ok(author);
  }
}
