package mx.dev.blank.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.entity.Author;
import mx.dev.blank.entity.Category;

@RequiredArgsConstructor
@Getter
@Builder
public class AuthorDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private final int id;
  private final String name;
  private final String firstName;
  private final String secondName;
  private final String birthday;

  public static AuthorDTO build(Author author){
    return AuthorDTO.builder()
            .id(author.getId())
            .name(author.getName())
            .firstName(author.getFirstName())
            .secondName(author.getSecondName())
            .birthday(new SimpleDateFormat("MM-dd-yyyy").format(author.getBirthday()))
            .build();
  }
}
