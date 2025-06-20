package elp.sistemazapateria.service.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import elp.sistemazapateria.controller.dto.VentaDetalleRequest;
import elp.sistemazapateria.controller.dto.VentaDetalleResponse;
import elp.sistemazapateria.model.VentaDetalle;
import elp.sistemazapateria.model.VentaDetallePK;
import elp.sistemazapateria.model.Calzado; // Para cargar datos del calzado si es necesario
import elp.sistemazapateria.model.Modelo; // Para cargar datos del modelo de calzado si es necesario

/**
 * Mapper para convertir entre entidades VentaDetalle y sus DTOs.
 */
@Component
public class VentaDetalleMapper {

	/**
	 * Convierte una colección de entidades VentaDetalle a una colección de
	 * VentaDetalleResponse DTOs.
	 * 
	 * @param listVentaDetalle La colección de entidades VentaDetalle.
	 * @return Una colección de VentaDetalleResponse DTOs.
	 */
	public Collection<VentaDetalleResponse> toListVentaDetalleToVentaDetalleResponse(
			Collection<VentaDetalle> listVentaDetalle) {
		if (listVentaDetalle == null) {
			return new ArrayList<>();
		}
		return listVentaDetalle.stream().map(this::toVentaDetalleToVentaDetalleResponse).collect(Collectors.toList());
	}

	/**
	 * Convierte una entidad VentaDetalle a un VentaDetalleResponse DTO.
	 * 
	 * @param ventaDetalle La entidad VentaDetalle.
	 * @return El VentaDetalleResponse DTO.
	 */
	public VentaDetalleResponse toVentaDetalleToVentaDetalleResponse(VentaDetalle ventaDetalle) {
		VentaDetalleResponse response = new VentaDetalleResponse();
		if (ventaDetalle != null) {
			if (ventaDetalle.getId() != null) {
				response.setIdVenta(ventaDetalle.getId().getIdVenta());
				response.setIdCalzado(ventaDetalle.getId().getIdCalzado());
			}
			response.setCantidad(ventaDetalle.getCantidad());
			response.setTotal(ventaDetalle.getTotal());
			response.setEstado(ventaDetalle.getEstado());

			// Opcional: Cargar marca/descripción de calzado si el objeto calzado está
			// cargado
			// Esto requiere que JPA haya cargado la relación 'calzado' en la entidad
			// VentaDetalle.
			if (ventaDetalle.getCalzado() != null) {
				response.setMarcaCalzado(ventaDetalle.getCalzado().getMarca());
				if (ventaDetalle.getCalzado().getModelo() != null) {
					response.setDescripcionModeloCalzado(ventaDetalle.getCalzado().getModelo().getDescripcion());
				}
			}
		}
		return response;
	}

	/**
	 * Convierte un VentaDetalleRequest DTO a una entidad VentaDetalle. Este método
	 * NO establece directamente las entidades Venta o Calzado relacionadas. Eso
	 * debe hacerse en la capa de servicio después de obtener las entidades
	 * completas de sus respectivos repositorios.
	 *
	 * @param request El VentaDetalleRequest DTO.
	 * @return La entidad VentaDetalle.
	 */
	public VentaDetalle toVentaDetalle(VentaDetalleRequest request) {
		if (request == null) {
			return null;
		}
		VentaDetalle ventaDetalle = new VentaDetalle();
		VentaDetallePK pk = new VentaDetallePK();

		// Los IDs de la PK vienen directamente del Request DTO
		pk.setIdVenta(request.getIdVenta());
		pk.setIdCalzado(request.getIdCalzado());

		ventaDetalle.setId(pk);
		ventaDetalle.setCantidad(request.getCantidad());
		ventaDetalle.setTotal(request.getTotal());
		ventaDetalle.setEstado(request.getEstado());

		// Las referencias a los objetos Venta y Calzado se establecerán en el servicio
		// después de que se carguen de sus repositorios.
		return ventaDetalle;
	}

	/**
	 * Actualiza los campos de una entidad VentaDetalle existente con los datos de
	 * un VentaDetalleRequest DTO. No actualiza los IDs de la clave primaria.
	 *
	 * @param request      El VentaDetalleRequest DTO con los datos actualizados.
	 * @param ventaDetalle La entidad VentaDetalle existente a actualizar.
	 */
	public void updateVentaDetalleFromDto(VentaDetalleRequest request, VentaDetalle ventaDetalle) {
		if (request == null || ventaDetalle == null) {
			return;
		}
		// No se actualizan los IDs de la clave primaria (idVenta, idCalzado) a través
		// de este método
		// ya que formarían parte de un nuevo registro si cambiaran.
		ventaDetalle.setCantidad(request.getCantidad());
		ventaDetalle.setTotal(request.getTotal());
		ventaDetalle.setEstado(request.getEstado());
		// Las referencias a los objetos Venta y Calzado también se manejarían en el
		// servicio si cambian
	}
}
