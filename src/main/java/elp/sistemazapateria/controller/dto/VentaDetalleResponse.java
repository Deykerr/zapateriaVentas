package elp.sistemazapateria.controller.dto;

import lombok.Data;

//DTO para los detalles de una venta (usado dentro de VentaResponse)
@Data
public class VentaDetalleResponse {
// Los IDs de la clave compuesta se pueden incluir si son relevantes para el frontend
private Integer idVenta; // El ID de la venta a la que pertenece este detalle
private Integer idCalzado; // El ID del calzado en este detalle
private String marcaCalzado; // Opcional: para mostrar la marca del calzado
private String descripcionModeloCalzado; // Opcional: para mostrar la descripción del modelo del calzado
private Integer cantidad;
private Double total;
private Boolean estado;
}