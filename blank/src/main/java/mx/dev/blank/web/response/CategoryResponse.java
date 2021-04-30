package mx.dev.blank.web.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.entity.Category;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class CategoryResponse {

    private final List<Category> categories;
}
