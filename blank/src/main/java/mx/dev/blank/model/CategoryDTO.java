
package mx.dev.blank.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.entity.Category;

@RequiredArgsConstructor
@Getter
@Builder
public class CategoryDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private final int id;
  private final String name;

  public static CategoryDTO build(Category category){
    return CategoryDTO.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
  }
}
