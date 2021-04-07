package mx.dev.blank.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "pages", nullable = false)
    private int pages;
    @Column(name = "isbn", nullable = false)
    private String isbn;
    @Column(name = "price", nullable = false)
    private float price ;
    @Column(name = "summary", nullable = false)
    private String summary;
    @Column(name = "editorial", nullable = false)
    private String editorial;
    @Column(name = "date_publication", nullable = false)
    private Date datePublication;
    @Column(name = "deleted", nullable = false, columnDefinition = "BIT default 0")
    private boolean deleted;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private Book(final String title,
                 final int pages,
                 final String isbn,
                 final float price,
                 final String summary,
                 final String editorial,
                 final Date datePublication,
                 final Category category){
        this.setTitle(title);
        this.setPages(pages);
        this.setIsbn(isbn);
        this.setPrice(price);
        this.setSummary(summary);
        this.setEditorial(editorial);
        this.setDatePublication(datePublication);
        this.setCategory(category);
    }

    public static Book newBook(final String title,
                               final int pages,
                               final String isbn,
                               final float price,
                               final String summary,
                               final String editorial,
                               final Date datePublication,
                               final Category category){
        return new Book(title, pages, isbn, price, summary, editorial, datePublication, category);
    }

}
