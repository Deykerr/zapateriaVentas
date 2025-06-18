package elp.sistemazapateria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "calzados")
//@NoArgsConstructor
public class Calzado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_calzado")
    private Integer idCalzado;

    private String nombre;

    @Column(name = "id_modelo", insertable = false, updatable = false) // FK gestionada por la relación @ManyToOne
    private Integer idmodelo; // Campo que guarda el ID de la categoría/modelo

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    // Recuperar a que categoria/modelo pertenece un calzado
    @ManyToOne
    @JoinColumn(name = "id_modelo", insertable = false, updatable = false)
   // private Modelo modelo;
}
