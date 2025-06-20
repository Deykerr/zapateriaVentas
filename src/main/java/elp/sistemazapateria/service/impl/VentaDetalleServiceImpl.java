package elp.sistemazapateria.service.impl;


import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elp.sistemazapateria.controller.dto.VentaDetalleRequest;
import elp.sistemazapateria.controller.dto.VentaDetalleResponse;
import elp.sistemazapateria.model.Calzado;
import elp.sistemazapateria.model.Venta;
import elp.sistemazapateria.model.VentaDetalle;
import elp.sistemazapateria.model.VentaDetallePK;
import elp.sistemazapateria.repository.VentaDetalleRepository;
import elp.sistemazapateria.repository.VentaRepository; // Para buscar la Venta
import elp.sistemazapateria.repository.CalzadoRepository; // Para buscar el Calzado
import elp.sistemazapateria.service.VentaDetalleService;
import elp.sistemazapateria.service.mapper.VentaDetalleMapper;

@Service
public class VentaDetalleServiceImpl implements VentaDetalleService {

	@Autowired
	private VentaDetalleRepository ventaDetalleRepository;

	@Autowired
	private VentaDetalleMapper ventaDetalleMapper;

	@Autowired
	private VentaRepository ventaRepository; // Para cargar el objeto Venta asociado

	@Autowired
	private CalzadoRepository calzadoRepository; // Para cargar el objeto Calzado asociado

	/**
	 * Método que trae toda la colección de detalles de venta de la BD y la mapea
	 * para responder como VentaDetalleResponse.
	 */
	@Override
	public Collection<VentaDetalleResponse> findAllVentaDetalle() {
		Collection<VentaDetalle> listVentaDetalles = ventaDetalleRepository.findAll();
		return ventaDetalleMapper.toListVentaDetalleToVentaDetalleResponse(listVentaDetalles);
	}

	/**
	 * Busca un detalle de venta por su ID compuesto.
	 * 
	 * @param id El ID compuesto (VentaDetallePK) del detalle de venta a buscar.
	 * @return El VentaDetalleResponse DTO si se encuentra, o null si no.
	 */
	@Override
	public VentaDetalleResponse findByIdVentaDetalle(VentaDetallePK id) {
		VentaDetalle ventaDetalle = ventaDetalleRepository.findById(id).orElse(null);
		return ventaDetalleMapper.toVentaDetalleToVentaDetalleResponse(ventaDetalle);
	}

	/**
	 * Guarda un nuevo detalle de venta en la base de datos. Requiere que existan la
	 * Venta y el Calzado referenciados por los IDs en el Request.
	 *
	 * @param request El VentaDetalleRequest DTO con los datos del nuevo detalle de
	 *                venta.
	 */
	@Override
	public void saveVentaDetalle(VentaDetalleRequest request) {
		// Verificar que la Venta y el Calzado referenciados existan
		Optional<Venta> ventaOpt = ventaRepository.findById(request.getIdVenta());
		if (ventaOpt.isEmpty()) {
			System.err.println(
					"Advertencia: Venta con ID " + request.getIdVenta() + " no encontrada para el detalle de venta.");
			// Aquí se podría lanzar una excepción ResourceNotFoundException
			return;
		}
		Optional<Calzado> calzadoOpt = calzadoRepository.findById(request.getIdCalzado());
		if (calzadoOpt.isEmpty()) {
			System.err.println("Advertencia: Calzado con ID " + request.getIdCalzado()
					+ " no encontrado para el detalle de venta.");
			// Aquí se podría lanzar una excepción ResourceNotFoundException
			return;
		}

		// Mapea el DTO a la entidad VentaDetalle (esto crea la PK)
		VentaDetalle ventaDetalleNew = ventaDetalleMapper.toVentaDetalle(request);

		// Establece las referencias a los objetos Venta y Calzado completos
		ventaDetalleNew.setVenta(ventaOpt.get());
		ventaDetalleNew.setCalzado(calzadoOpt.get());

		// Guarda el nuevo detalle de venta
		ventaDetalleRepository.save(ventaDetalleNew);
		System.out.println("Nuevo detalle de venta guardado con éxito.");
	}

	/**
	 * Actualiza un detalle de venta existente en la base de datos. Requiere que el
	 * detalle de venta original exista. Los IDs de la PK (idVenta, idCalzado) no se
	 * pueden cambiar directamente a través de una actualización; si cambian, sería
	 * un nuevo registro.
	 *
	 * @param id      El ID compuesto (VentaDetallePK) del detalle de venta a
	 *                actualizar.
	 * @param request El VentaDetalleRequest DTO con los datos actualizados.
	 */
	@Override
	public void updateVentaDetalle(VentaDetallePK id, VentaDetalleRequest request) {
		VentaDetalle ventaDetalleExistente = ventaDetalleRepository.findById(id).orElse(null);

		if (ventaDetalleExistente != null) {
			// Verificar si los IDs de Venta o Calzado en el request son diferentes y
			// válidos
			// Si son diferentes, esto indicaría un cambio en la PK, lo cual no es una
			// actualización.
			// Para simplicidad, asumimos que los IDs de la PK no cambian en una
			// actualización.
			// Los campos de cantidad, total, estado se pueden actualizar.
			ventaDetalleMapper.updateVentaDetalleFromDto(request, ventaDetalleExistente);

			// Si los objetos Venta o Calzado relacionados deben cambiar,
			// se necesitaría lógica adicional aquí para cargar y reasignar esas relaciones.
			// Por ahora, asumimos que las relaciones subyacentes de la PK no cambian en una
			// 'update'.

			ventaDetalleRepository.save(ventaDetalleExistente);
			System.out.println("Detalle de venta actualizado con éxito.");
		} else {
			System.err.println("Advertencia: Detalle de venta con ID " + id + " no encontrado para la actualización.");
		}
	}

	/**
	 * Elimina un detalle de venta de la base de datos por su ID compuesto.
	 *
	 * @param id El ID compuesto (VentaDetallePK) del detalle de venta a eliminar.
	 */
	@Override
	public void deleteVentaDetalle(VentaDetallePK id) {
		VentaDetalle ventaDetalle = ventaDetalleRepository.findById(id).orElse(null);
		if (ventaDetalle != null) {
			ventaDetalleRepository.delete(ventaDetalle);
			System.out.println("Detalle de venta eliminado con éxito.");
		} else {
			System.err.println("Advertencia: Detalle de venta con ID " + id + " no encontrado para la eliminación.");
		}
	}
}

