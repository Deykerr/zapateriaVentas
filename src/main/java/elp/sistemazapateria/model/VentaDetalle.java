package elp.sistemazapateria.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table (name = "venta_Detalle")
@Data //se agrega el @Data
public class VentaDetalle {

	@EmbeddedId
	private VentaDetallePK id;
	
	private Integer cantidad;
	private Double total;
	private Boolean estado;
	@ManyToOne
	@MapsId("idVenta")
	@JoinColumn(name = "id_venta", insertable = false, updatable = false)
	@ToString.Exclude 
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "id_calzado", insertable = false, updatable = false)
	@ToString.Exclude 
	private Calzado calzado;
}


