package mx.dev.blank.entity;

import lombok.*;
import mx.dev.blank.web.controller.request.BookRequest;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ranking")
@EqualsAndHashCode(of = {"id"})
@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Ranking implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "rank", nullable = false)
    private String rank;
}
