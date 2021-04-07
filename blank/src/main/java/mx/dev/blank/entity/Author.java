package mx.dev.blank.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "author")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "second_name", nullable = false)
    private String second_name;

    @Column(name = "birthday")
    private Date birthday;

}
