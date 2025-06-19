package elp.sistemazapateria.model;

import jakarta.persistence.*;

@Entity
@Table (name = "venta_Detalle")
public class VentaDetalle {

	@EmbeddedId
	private VentaDetallePK id;
	
	private Integer cantidad;
	private Double total;
	private Boolean estado;
	@ManyToOne
	@MapsId("idVenta")
	@JoinColumn(name = "id_venta", insertable = false, updatable = false)
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "id_calzado", insertable = false, updatable = false)
	private Calzado calzado;
}
