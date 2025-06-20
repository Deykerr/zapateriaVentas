package elp.sistemazapateria.controller.dto;

import lombok.Getter;
import lombok.Setter;
//DTO para los detalles de una venta (usado dentro de VentaRequest)
@Getter
@Setter
public class VentaDetalleRequest {
	   private Integer idVenta;   // Nuevo: ID de la venta a la que pertenece el detalle
	    private Integer idCalzado; // ID del calzado
	    private Integer cantidad;
	    private Double total;
	    private Boolean estado;
}
