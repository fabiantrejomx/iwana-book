package mx.dev.blank.entity;

import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Categorias")
@EqualsAndHashCode(of = {"id"})
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "caid")
    @GenericGenerator(name = "caid", strategy = "caid2")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "Nombre")
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<Pertenecen> pertenecens = new ArrayList<>();

    @OneToMany(mappedBy = "categoria2")
    private List<Pertenecian> pertenecians = new ArrayList<>();
}