package mx.dev.blank.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "authors")
@EqualsAndHashCode(of = {"id"})
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "first_surname", nullable = false)
    private String firstSurname;

    @Column(name = "second_surname", nullable = false)
    private String secondSurname;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

}
