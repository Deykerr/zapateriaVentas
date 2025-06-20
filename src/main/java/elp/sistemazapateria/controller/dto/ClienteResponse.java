package elp.sistemazapateria.controller.dto;

import lombok.Data;


@Data
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String apellidos;
    private Long celular;
    private String correo;
}
