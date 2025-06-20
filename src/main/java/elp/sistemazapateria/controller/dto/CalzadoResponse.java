package elp.sistemazapateria.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data; // Usamos @Data para simplificar (incluye @Getter y @Setter)
import lombok.NoArgsConstructor;

@Data // Genera automáticamente getters, setters, toString, equals y hashCode

public class CalzadoResponse {

	private Integer idCalzado;
	private String marca;
	private Integer idModelo; // Puedes incluir solo el ID del modelo
	private String descripcionModelo; // Opcional: descripción del modelo para facilitar la visualización
	private Double precioVenta;
	private Integer cantidadStock;
	private Boolean estado;

}
