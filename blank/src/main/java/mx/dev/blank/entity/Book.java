package mx.dev.blank.entity;

import lombok.*;
import mx.dev.blank.web.controller.request.BookRequest;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books")
@EqualsAndHashCode(of = {"id"})
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

    public Book(final String title, final int pagesNumber, final String authors, final String editorial, final int isbn,
                final String category, final String price, final String summary, final int publicationYear) {
        this.title = title;
        this.pagesNumber = pagesNumber;
        this.authors = authors;
        this.editorial = editorial;
        this.isbn = isbn;
        this.category = category;
        this.price = price;
        this.summary = summary;
        this.publicationYear = publicationYear;
    }

    public void update(final BookRequest request) {
        this.title = request.getTitle();
        this.pagesNumber = request.getPagesNumber();
        this.authors = request.getAuthors();
        this.editorial = request.getEditorial();
        this.isbn = request.getIsbn();
        this.category = request.getCategory();
        this.price = request.getPrice();
        this.summary = request.getSummary();
        this.publicationYear = request.getPublicationYear();
    }


}
