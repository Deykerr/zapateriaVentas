package elp.sistemazapateria.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {

	private String nombre;
	private String apellidos;
	private Long celular;
	private String direccion;
	private String correoElectronico;

}
