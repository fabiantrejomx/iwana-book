package mx.dev.blank.entity;

import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "Autores")
@EqualsAndHashCode(of = {"id"})
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "auid")
    @GenericGenerator(name = "auid", strategy = "auid2")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "ApeP")
    private String apep;

    @Column(name = "ApeM")
    private String apem;

    @Column(name = "Nacimiento")
    private Date nacimiento;
}
