package mx.dev.blank.entity;

import lombok.*;
import mx.dev.blank.web.form.BookForm;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "book")
@EqualsAndHashCode(of = {"id"})
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "num_pages")
    private Integer numPages;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_category", nullable = false)
    private Category categoryID;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_editor", nullable = false)
    private Editor editorID;

    @Column(name = "isbn", nullable = false)
    private BigDecimal isbn;

    @Column(name = "price")
    private Integer price;

    @Column(name = "resume")
    private String resume;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_public", nullable = false)
    private Date datePublished;

    public Book(final Integer id,
                final String title,
                final Integer numPages,
                final Category categoryID,
                final Editor editorID,
                final BigDecimal isbn,
                final Integer price,
                final String resume,
                final Date datePublished
    ){
        this.id=id;
        this.title=title;
        this.numPages=numPages;
        this.categoryID=categoryID;
        this.editorID=editorID;
        this.isbn=isbn;
        this.price=price;
        this.resume=resume;
        this.datePublished=datePublished;
    }

    public void update(final BookForm form) {
        this.title = form.getTitle();
        this.numPages = form.getNumPages();
        this.categoryID = form.getCategoryID();
        this.editorID = form.getEditorID();
        this.isbn = form.getIsbn();
        this.price = form.getPrice();
        this.resume = form.getResume();
        this.datePublished = form.getDatePublished();
    }
}
