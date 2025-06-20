package elp.sistemazapateria.controller.dto;

import lombok.Getter;

import lombok.Setter;

@Getter // Genera automáticamente los métodos getter
@Setter // Genera automáticamente los métodos setter

public class CalzadoRequest {
	private String marca;
	private Integer idModelo; // Referencia al ID del Modelo

	private Double precioVenta; // Coincide con el tipo de la entidad
	private Integer cantidadStock; // Coincide con el tipo de la entidad

	private Boolean estado;

}