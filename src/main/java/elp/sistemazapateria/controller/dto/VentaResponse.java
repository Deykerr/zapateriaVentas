package elp.sistemazapateria.controller.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VentaResponse {
	 private Integer identificador;
	 private String idCliente;
		private LocalDateTime fecha;
		private String metodoPago;
		private Cliente cliente;
}
