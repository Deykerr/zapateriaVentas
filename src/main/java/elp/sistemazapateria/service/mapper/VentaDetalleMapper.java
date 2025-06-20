package elp.sistemazapateria.service.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import elp.sistemazapateria.controller.dto.VentaDetalleRequest;
import elp.sistemazapateria.controller.dto.VentaDetalleResponse;
import elp.sistemazapateria.model.VentaDetalle;
import elp.sistemazapateria.model.VentaDetallePK;
import elp.sistemazapateria.model.Calzado; // Para cargar datos del calzado 
import elp.sistemazapateria.model.Modelo; // Para cargar datos del modelo de calzado 

@Component
public class VentaDetalleMapper {

	 
	public Collection<VentaDetalleResponse> toListVentaDetalleToVentaDetalleResponse(
			Collection<VentaDetalle> listVentaDetalle) {
		if (listVentaDetalle == null) {
			return new ArrayList<>();
		}
		return listVentaDetalle.stream().map(this::toVentaDetalleToVentaDetalleResponse).collect(Collectors.toList());
	}

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

			if (ventaDetalle.getCalzado() != null) {
				response.setMarcaCalzado(ventaDetalle.getCalzado().getMarca());
				if (ventaDetalle.getCalzado().getModelo() != null) {
					response.setDescripcionModeloCalzado(ventaDetalle.getCalzado().getModelo().getDescripcion());
				}
			}
		}
		return response;
	}


	public VentaDetalle toVentaDetalle(VentaDetalleRequest request) {
		if (request == null) {
			return null;
		}
		VentaDetalle ventaDetalle = new VentaDetalle();
		VentaDetallePK pk = new VentaDetallePK();

		pk.setIdVenta(request.getIdVenta());
		pk.setIdCalzado(request.getIdCalzado());

		ventaDetalle.setId(pk);
		ventaDetalle.setCantidad(request.getCantidad());
		ventaDetalle.setTotal(request.getTotal());
		ventaDetalle.setEstado(request.getEstado());
		return ventaDetalle;
	}

	//Actualiza los campos de una entidad VentaDetalle existente con los datos de
	
	public void updateVentaDetalleFromDto(VentaDetalleRequest request, VentaDetalle ventaDetalle) {
		if (request == null || ventaDetalle == null) {
			return;
		}
		ventaDetalle.setCantidad(request.getCantidad());
		ventaDetalle.setTotal(request.getTotal());
		ventaDetalle.setEstado(request.getEstado());
	}
}
