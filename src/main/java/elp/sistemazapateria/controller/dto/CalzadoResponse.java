package elp.sistemazapateria.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data; // Usamos @Data para simplificar (incluye @Getter y @Setter)
import lombok.NoArgsConstructor;

@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class CalzadoResponse {

    private Integer idCalzado; // ID del calzado
    private String nombre;
    private Integer idmodelo; // ID del modelo/categoría
    private String nombreCategoria; // Nombre de la categoría (para visualización, se mapeará desde la entidad Categoria)
    private Double precioVenta;
    private Integer cantidadStock;
}
