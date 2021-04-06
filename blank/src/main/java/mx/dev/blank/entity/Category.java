package mx.dev.blank.entity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "category")
@EqualsAndHashCode(of = {"id"})
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "category", nullable = false)
    private String category;
}
