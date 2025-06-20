package elp.sistemazapateria.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Table(name = "modelos")
@Data
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private Integer idModelo;

    private String descripcion;
    private Boolean estado;

    @OneToMany(mappedBy = "modelo")
    private List<Calzado> calzados;
}
