package mx.dev.blank.entity;

import lombok.*;
import mx.dev.blank.web.controller.request.BookRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "authors_id", nullable = false)
    private Author authors;

    @Column(name = "editorial", nullable = false)
    private String editorial;

    @Column(name = "isbn", nullable = false)
    private int isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "publication_date", nullable = false)
    private Date publicationDate;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "ranking_id", nullable = false)
    private Ranking ranking;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    public Book(final String title, final int pagesNumber, final Author authors, final String editorial, final int isbn,
                final Category category, final int price, final String summary, final Date publicationDate) {
        this.title = title;
        this.pagesNumber = pagesNumber;
        this.authors = authors;
        this.editorial = editorial;
        this.isbn = isbn;
        this.category = category;
        this.price = price;
        this.summary = summary;
        this.publicationDate = publicationDate;
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
        this.publicationDate = request.getPublicationDate();
    }


}
