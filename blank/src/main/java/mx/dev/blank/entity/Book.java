package mx.dev.blank.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category categoryID;

    @OneToOne
    @JoinColumn(name = "id_editor", nullable = false)
    private Editor editorID;

    @Column(name = "isbn", nullable = false)
    private Integer isbn;

    @Column(name = "price")
    private Integer price;

    @Column(name = "resume")
    private String resume;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_public", nullable = false)
    private Date datePublished;
}
