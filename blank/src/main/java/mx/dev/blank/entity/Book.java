package mx.dev.blank.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
@EqualsAndHashCode(of = {"id"})
@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "pages_number", nullable = false)
    private int pagesNumber;

    @Column(name = "authors", nullable = false)
    private String authors;

    @Column(name = "editorial", nullable = false)
    private String editorial;

    @Column(name = "isbn", nullable = false)
    private int isbn;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

}
