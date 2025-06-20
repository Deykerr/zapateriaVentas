package elp.sistemazapateria.service;

import java.util.Collection;

import elp.sistemazapateria.controller.dto.VentaDetalleRequest;
import elp.sistemazapateria.controller.dto.VentaDetalleResponse;
import elp.sistemazapateria.model.VentaDetallePK; // Importa la clave compuesta

public interface VentaDetalleService {
	// Para traer todos los detalles de venta
	Collection<VentaDetalleResponse> findAllVentaDetalle();

	// Para buscar un detalle de venta por su ID compuesto
	VentaDetalleResponse findByIdVentaDetalle(VentaDetallePK id);

	// Para crear un nuevo detalle de venta
	void saveVentaDetalle(VentaDetalleRequest request);

	// Para actualizar un detalle de venta existente
	void updateVentaDetalle(VentaDetallePK id, VentaDetalleRequest request);

	// Para eliminar un detalle de venta por su ID compuesto
	void deleteVentaDetalle(VentaDetallePK id);
}
