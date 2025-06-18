package elp.sistemazapateria.model;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

public class VentaDetalle {

	@EmbeddedId
	private ComprasProductoPK id;
	
	private Integer cantidad;
	private Double total;
	private Boolean estado;
	@ManyToOne
	@MapsId("idCompra")
	@JoinColumn(name = "id_compra", insertable = false, updatable = false)
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "id_producto", insertable = false, updatable = false)
	private Producto producto;
}
