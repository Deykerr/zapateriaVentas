package elp.sistemazapateria.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elp.sistemazapateria.controller.dto.VentaRequest;
import elp.sistemazapateria.controller.dto.VentaResponse;
import elp.sistemazapateria.model.Venta;
import elp.sistemazapateria.model.Cliente;
import elp.sistemazapateria.repository.VentaRepository;
import elp.sistemazapateria.repository.ClienteRepository;
import elp.sistemazapateria.service.VentaService;
import elp.sistemazapateria.service.mapper.VentaMapper;

@Service
public class VentaServiceImpl implements VentaService {
	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private VentaMapper ventaMapper;

	@Autowired
	private ClienteRepository clienteRepository; //

	@Override
	public Collection<VentaResponse> findAllVenta() {
		Collection<Venta> listVentas = ventaRepository.findAll();
		return ventaMapper.toListVentaToVentaResponse(listVentas);
	}

	// Busca una venta por su ID.

	@Override
	public VentaResponse findByIdVenta(Integer idVenta) {
		Venta venta = ventaRepository.findById(idVenta).orElse(null);
		return ventaMapper.toVentaToVentaResponse(venta);
	}

	// Guarda una nueva venta en la base de datos.

	@Override
	public void saveVenta(VentaRequest request) {

		if (request.getIdCliente() != null) {
			Optional<Cliente> clienteOpt = clienteRepository.findById(request.getIdCliente());
			if (clienteOpt.isEmpty()) {
				System.err.println(
						"Advertencia: Cliente con ID " + request.getIdCliente() + " no encontrado para la venta.");

			}
		}

		// Usa el mapper para convertir el DTO de solicitud a la entidad Venta
		Venta ventaNew = ventaMapper.toVenta(request);

		ventaRepository.save(ventaNew);
		System.out.println("Nueva venta guardada con éxito.");
	}

	// Actualiza una venta existente en la base de datos.

	@Override
	public void updateVenta(Integer id, VentaRequest request) {
		Venta ventaExistente = ventaRepository.findById(id).orElse(null);

		if (ventaExistente != null) {
			if (request.getIdCliente() != null && !request.getIdCliente().equals(ventaExistente.getIdCliente())) {
				Optional<Cliente> clienteOpt = clienteRepository.findById(request.getIdCliente());
				if (clienteOpt.isEmpty()) {
					System.err.println("Advertencia: Cliente con ID " + request.getIdCliente()
							+ " no encontrado para la actualización de la venta.");
				}
			}

			// Usa el mapper para actualizar los campos de la venta existente
			ventaMapper.updateVentaFromDto(request, ventaExistente);
			// Guarda la venta actualizada en la base de datos
			ventaRepository.save(ventaExistente);
			System.out.println("Venta actualizada con éxito.");
		} else {
			System.err.println("Advertencia: Venta con ID " + id + " no encontrada para la actualización.");
		}
	}

	// Elimina una venta de la base de datos por su ID.

	@Override
	public void deleteVenta(Integer idVenta) {
		Venta venta = ventaRepository.findById(idVenta).orElse(null);
		if (venta != null) {
			ventaRepository.delete(venta);
			System.out.println("Venta eliminada con éxito.");
		} else {
			System.err.println("Advertencia: Venta con ID " + idVenta + " no encontrada para la eliminación.");
		}
	}
}
