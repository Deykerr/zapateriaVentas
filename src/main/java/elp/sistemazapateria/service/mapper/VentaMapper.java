package elp.sistemazapateria.service.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import elp.sistemazapateria.controller.dto.VentaDetalleRequest;
import elp.sistemazapateria.controller.dto.VentaDetalleResponse;
import elp.sistemazapateria.controller.dto.VentaRequest;
import elp.sistemazapateria.controller.dto.VentaResponse;
import elp.sistemazapateria.model.Venta;
import elp.sistemazapateria.model.VentaDetalle;
import elp.sistemazapateria.model.VentaDetallePK;
import elp.sistemazapateria.model.Cliente;
import elp.sistemazapateria.model.Calzado;
import elp.sistemazapateria.model.Modelo;

@Component
public class VentaMapper {
	public Collection<VentaResponse> toListVentaToVentaResponse(Collection<Venta> listVenta) {
		if (listVenta == null) {
			return new ArrayList<>();
		}
		return listVenta.stream().map(this::toVentaToVentaResponse).collect(Collectors.toList());
	}

	public VentaResponse toVentaToVentaResponse(Venta venta) {
		VentaResponse ventaResponse = new VentaResponse();
		if (venta != null) {
			ventaResponse.setIdVenta(venta.getIdVenta());
			ventaResponse.setIdCliente(venta.getIdCliente());
			ventaResponse.setFecha(venta.getFecha());
			ventaResponse.setMetodoPago(venta.getMetodoPago());
			ventaResponse.setEstado(venta.getEstado());

			if (venta.getCliente() != null) {
				ventaResponse
						.setNombreCliente(venta.getCliente().getNombre() + " " + venta.getCliente().getApellidos());
			}

			if (venta.getCalzados() != null) {
				ventaResponse.setDetalles(venta.getCalzados().stream().map(this::toVentaDetalleToVentaDetalleResponse)
						.collect(Collectors.toList()));
			} else {
				ventaResponse.setDetalles(new ArrayList<>());
			}
		}
		return ventaResponse;
	}

	public Venta toVenta(VentaRequest request) {
		if (request == null) {
			return null;
		}
		Venta venta = new Venta();
		venta.setIdCliente(request.getIdCliente());
		venta.setFecha(request.getFecha() != null ? request.getFecha() : java.time.LocalDateTime.now());
		venta.setMetodoPago(request.getMetodoPago());
		venta.setEstado(request.getEstado());

		if (request.getDetalles() != null && !request.getDetalles().isEmpty()) {
			venta.setCalzados(request.getDetalles().stream()

					.map(detalleRequest -> toVentaDetalleForNewVenta(detalleRequest, venta))
					.collect(Collectors.toList()));
		} else {
			venta.setCalzados(new ArrayList<>());
		}
		return venta;
	}

	public void updateVentaFromDto(VentaRequest request, Venta venta) {
		if (request == null || venta == null) {
			return;
		}
		venta.setIdCliente(request.getIdCliente());
		venta.setFecha(request.getFecha() != null ? request.getFecha() : venta.getFecha());
		venta.setMetodoPago(request.getMetodoPago());
		venta.setEstado(request.getEstado());

		// --- Lógica para la actualización de VentaDetalle ---
		if (request.getDetalles() == null) {
			venta.getCalzados().clear();
		} else {
			// Mapea los detalles del request a un mapa por su PK para acceso fácil 
			Map<VentaDetallePK, VentaDetalleRequest> newDetailsMap = request.getDetalles().stream()
					.collect(Collectors.toMap(req -> {
						VentaDetallePK pk = new VentaDetallePK();
						pk.setIdVenta(venta.getIdVenta()); // Usamos el ID de la venta existente
						pk.setIdCalzado(req.getIdCalzado());
						return pk;
					}, Function.identity()));

			List<VentaDetalle> detailsToRemove = new ArrayList<>();

			for (VentaDetalle existingDetail : venta.getCalzados()) {
				VentaDetallePK existingPk = existingDetail.getId();
				if (newDetailsMap.containsKey(existingPk)) {
					VentaDetalleRequest updatedRequest = newDetailsMap.get(existingPk);
					updateVentaDetalleFromDto(updatedRequest, existingDetail);
					newDetailsMap.remove(existingPk);
				} else {
					detailsToRemove.add(existingDetail);
				}
			}

			// Eliminar detalles marcados
			venta.getCalzados().removeAll(detailsToRemove);

			// Añadir nuevos detalles 
			for (VentaDetalleRequest newDetailRequest : newDetailsMap.values()) {
				VentaDetalle newDetail = toVentaDetalleForNewVenta(newDetailRequest, venta); 
				venta.getCalzados().add(newDetail);
			}
		}
	}

	public VentaDetalle toVentaDetalleForNewVenta(VentaDetalleRequest request, Venta venta) {
		if (request == null) {
			return null;
		}
		VentaDetalle ventaDetalle = new VentaDetalle();
		VentaDetallePK pk = new VentaDetallePK();

		if (venta.getIdVenta() != null) {
			pk.setIdVenta(venta.getIdVenta());
		} else {
			// Si la venta aún no tiene ID (ej. al crear una venta nueva),
			// Hibernate/JPA lo gestionará por @MapsId después del guardado de la Venta
			// principal.
		}

		pk.setIdCalzado(request.getIdCalzado());

		ventaDetalle.setId(pk);
		ventaDetalle.setCantidad(request.getCantidad());
		ventaDetalle.setTotal(request.getTotal());
		ventaDetalle.setEstado(request.getEstado());
		ventaDetalle.setVenta(venta); // Establecer la referencia bidireccional a la Venta

		return ventaDetalle;
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

	public void updateVentaDetalleFromDto(VentaDetalleRequest request, VentaDetalle ventaDetalle) {
		if (request == null || ventaDetalle == null) {
			return;
		}
		ventaDetalle.setCantidad(request.getCantidad());
		ventaDetalle.setTotal(request.getTotal());
		ventaDetalle.setEstado(request.getEstado());
	}
}
