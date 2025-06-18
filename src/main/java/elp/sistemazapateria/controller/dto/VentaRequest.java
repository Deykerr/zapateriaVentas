package elp.sistemazapateria.controller.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class VentaRequest {
	private String idCliente;
	//private LocalDateTime fecha;
	private String metodoPago;
	private Cliente cliente;
	
}
