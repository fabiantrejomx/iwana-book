package mx.dev.blank.entity;

import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Escritos")
@EqualsAndHashCode(of = {"id"})
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Escrito implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "esid")
    @GenericGenerator(name = "esid", strategy = "esid2")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "Autor")
    private int autor;

    @Column(name = "Libro")
    private int libros;
}
