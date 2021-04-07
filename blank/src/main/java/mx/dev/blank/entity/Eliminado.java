package mx.dev.blank.entity;

import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Eliminados")
@EqualsAndHashCode(of = {"id"})
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Eliminado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "elid")
    @GenericGenerator(name = "elid", strategy = "elid2")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "Paginas")
    private int paginas;

    @Column(name = "Autores")
    private int autores;

    @Column(name = "Editorial")
    private String editorial;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "Categorias")
    private int categorias;

    @Column(name = "Precio")
    private float precio;

    @Column(name = "Resumen")
    private String resumen;

    @Column(name = "Publicacion")
    private Date publicacion;

    @OneToMany(mappedBy = "eliminado")
    private List<Borrado> borrado = new ArrayList<>();

    @OneToMany(mappedBy = "eliminado2")
    private List<Pertenecian> pertenecians = new ArrayList<>();
}
