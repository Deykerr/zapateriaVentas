package elp.sistemazapateria.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Genera automáticamente los métodos getter
@Setter // Genera automáticamente los métodos setter
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class CalzadoRequest {

  
    private String nombre;

 
    private Integer idmodelo; // Clave foránea para la categoría/modelo

 
    private Double precioVenta; // Coincide con el tipo de la entidad

    private Integer cantidadStock; // Coincide con el tipo de la entidad
}