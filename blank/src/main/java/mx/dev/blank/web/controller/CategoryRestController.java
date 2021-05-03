package mx.dev.blank.web.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.entity.Author;
import mx.dev.blank.entity.Category;
import mx.dev.blank.service.CategoryService;
import mx.dev.blank.web.request.CategoryRequest;
import mx.dev.blank.web.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryRestController {

  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<BaseResponse> createBook(
      @Valid @RequestBody final CategoryRequest categoryRequest, final BindingResult errors) {

    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(BaseResponse.fail(errors.getAllErrors()));
    }

    categoryService.createCategory(categoryRequest);

    return ResponseEntity.ok(BaseResponse.success("Category created successfully"));
  }

  @PutMapping(path = "/{categoryId}")
  public ResponseEntity<BaseResponse> updateCategory(
      @PathVariable(name = "categoryId") final int categoryId,
      @Valid @RequestBody final CategoryRequest categoryRequest,
      final BindingResult errors) {

    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(BaseResponse.fail(errors.getAllErrors()));
    }

    categoryService.updateCategory(categoryId, categoryRequest);

    return ResponseEntity.ok(BaseResponse.success("Category updated successfully"));
  }

  @DeleteMapping(path = "/{categoryId}")
  public ResponseEntity<BaseResponse> deleteCategory(
      @PathVariable(name = "categoryId") final int categoryId) {

    categoryService.deleteCategory(categoryId);
    return ResponseEntity.ok(BaseResponse.success("Category deleted successfully"));
  }

  @GetMapping(value = "/list")
  public ResponseEntity<List<Category>> getCategories() {

    final List<Category> categories = categoryService.findAll();
    return ResponseEntity.ok(categories);
  }

  @GetMapping(value = "/{categoryId}")
  public ResponseEntity<Category> getByCategoryId(@PathVariable int categoryId) {

    final Category category = categoryService.getByCategoryId(categoryId);
    return ResponseEntity.ok(category);
  }




}
