package elp.sistemazapateria.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class VentaResponse {
	
	private Integer idVenta;
	private Long idCliente;
	private String nombreCliente; //para mostrar el nombre del cliente directamente
	private LocalDateTime fecha;
	private String metodoPago;
	private String estado;
	private List<VentaDetalleResponse> detalles; // Lista de detalles de la venta
}
