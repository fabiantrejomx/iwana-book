package mx.dev.blank.web.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.service.AuthorService;
import mx.dev.blank.web.request.AuthorRequest;
import mx.dev.blank.web.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
}
