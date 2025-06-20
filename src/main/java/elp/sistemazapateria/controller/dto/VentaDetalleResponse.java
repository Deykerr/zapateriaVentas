package elp.sistemazapateria.controller.dto;

import lombok.Data;


@Data
public class VentaDetalleResponse {

private Integer idVenta; // El ID de la venta a la que pertenece este detalle
private Integer idCalzado; // El ID del calzado en este detalle
private String marcaCalzado; // para mostrar la marca del calzado
private String descripcionModeloCalzado; // para mostrar la descripción del modelo del calzado
private Integer cantidad;
private Double total;
private Boolean estado;
}