package mx.dev.blank.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "author")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "first_name", nullable = false, length = 50)
  private String first_name;

  @Column(name = "second_name", length = 50)
  private String second_name;

  @Column(name = "birthday")
  private Date birthday;

  @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
  private Set<Book> books = new HashSet<>();
}
