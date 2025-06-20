package elp.sistemazapateria.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "calzados")
@NoArgsConstructor
@Data
public class Calzado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_calzado")
    private Integer idCalzado;

    private String marca;

    @Column(name = "id_modelo")
    private Integer idModelo; 

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;
    
    private Boolean estado;

    
    @ManyToOne
    @JoinColumn(name = "id_modelo", insertable = false, updatable = false)
    private Modelo modelo;
}

