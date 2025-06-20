package elp.sistemazapateria.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class VentaRequest {

	private Long idCliente; // ID del cliente asociado a la venta
    private LocalDateTime fecha; 
    private String metodoPago;
    private String estado;
    private List<VentaDetalleRequest> detalles; // Lista de detalles de la venta
	
}
