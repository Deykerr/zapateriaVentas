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
import elp.sistemazapateria.repository.VentaRepository; 
import elp.sistemazapateria.repository.CalzadoRepository;
import elp.sistemazapateria.service.VentaDetalleService;
import elp.sistemazapateria.service.mapper.VentaDetalleMapper;

@Service
public class VentaDetalleServiceImpl implements VentaDetalleService {

	@Autowired
	private VentaDetalleRepository ventaDetalleRepository;

	@Autowired
	private VentaDetalleMapper ventaDetalleMapper;

	@Autowired
	private VentaRepository ventaRepository; 

	@Autowired
	private CalzadoRepository calzadoRepository; 

	// Método que trae toda la colección de detalles de venta de la BD y la mapea
	
	@Override
	public Collection<VentaDetalleResponse> findAllVentaDetalle() {
		Collection<VentaDetalle> listVentaDetalles = ventaDetalleRepository.findAll();
		return ventaDetalleMapper.toListVentaDetalleToVentaDetalleResponse(listVentaDetalles);
	}

	//Busca un detalle de venta por su ID compuesto.
	 
	@Override
	public VentaDetalleResponse findByIdVentaDetalle(VentaDetallePK id) {
		VentaDetalle ventaDetalle = ventaDetalleRepository.findById(id).orElse(null);
		return ventaDetalleMapper.toVentaDetalleToVentaDetalleResponse(ventaDetalle);
	}

	//Guarda un nuevo detalle de venta en la base de datos.

	@Override
	public void saveVentaDetalle(VentaDetalleRequest request) {
		// Verificar que la Venta y el Calzado referenciados existan
		Optional<Venta> ventaOpt = ventaRepository.findById(request.getIdVenta());
		if (ventaOpt.isEmpty()) {
			System.err.println(
					"Advertencia: Venta con ID " + request.getIdVenta() + " no encontrada para el detalle de venta.");
			return;
		}
		Optional<Calzado> calzadoOpt = calzadoRepository.findById(request.getIdCalzado());
		if (calzadoOpt.isEmpty()) {
			System.err.println("Advertencia: Calzado con ID " + request.getIdCalzado()
					+ " no encontrado para el detalle de venta.");
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

	//Actualiza un detalle de venta existente en la base de datos
	
	@Override
	public void updateVentaDetalle(VentaDetallePK id, VentaDetalleRequest request) {
		VentaDetalle ventaDetalleExistente = ventaDetalleRepository.findById(id).orElse(null);

		if (ventaDetalleExistente != null) {
	
			ventaDetalleMapper.updateVentaDetalleFromDto(request, ventaDetalleExistente);
			ventaDetalleRepository.save(ventaDetalleExistente);
			System.out.println("Detalle de venta actualizado con éxito.");
		} else {
			System.err.println("Advertencia: Detalle de venta con ID " + id + " no encontrado para la actualización.");
		}
	}

	//Elimina un detalle de venta de la base de datos por su ID compuesto.
	
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
