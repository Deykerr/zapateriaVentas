package elp.sistemazapateria.service.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import elp.sistemazapateria.controller.dto.ClienteRequest;
import elp.sistemazapateria.controller.dto.ClienteResponse;
import elp.sistemazapateria.model.Cliente;

@Component
public class ClienteMapper {

	public Collection<ClienteResponse> toListClienteToClienteResponse(Collection<Cliente> listCliente) {
		Collection<ClienteResponse> listClienteResponses = new ArrayList<>();

		if (listCliente != null && !listCliente.isEmpty()) {
			for (Cliente cliente : listCliente) {
				ClienteResponse clienteResponse = new ClienteResponse();
				clienteResponse.setId(cliente.getId());
				clienteResponse.setNombre(cliente.getNombre());
				clienteResponse.setApellidos(cliente.getApellidos());
				clienteResponse.setCelular(cliente.getCelular());
				clienteResponse.setCorreo(cliente.getCorreo());
				listClienteResponses.add(clienteResponse);
			}
		}

		return listClienteResponses;
	}

	public ClienteResponse toClienteToClienteResponse(Cliente cliente) {
		ClienteResponse clienteResponse = new ClienteResponse();
		if (cliente != null) {
			clienteResponse.setId(cliente.getId());
			clienteResponse.setNombre(cliente.getNombre());
			clienteResponse.setApellidos(cliente.getApellidos());
			clienteResponse.setCelular(cliente.getCelular());
			clienteResponse.setCorreo(cliente.getCorreo());

		}
		return clienteResponse;
	}

// ClienteMapper.java - Añadir
	public Cliente toCliente(ClienteRequest clienteRequest) {
		if (clienteRequest == null) {
			return null;
		}
		Cliente cliente = new Cliente();
		cliente.setNombre(clienteRequest.getNombre());
		cliente.setApellidos(clienteRequest.getApellidos());
		cliente.setCelular(clienteRequest.getCelular());
		cliente.setCorreo(clienteRequest.getCorreo());
		return cliente;
	}

	// Opcional: Para actualizar un cliente existente
	public void updateClienteFromDto(ClienteRequest clienteRequest, Cliente cliente) {
		if (clienteRequest == null || cliente == null) {
			return;
		}
		cliente.setNombre(clienteRequest.getNombre());
		cliente.setApellidos(clienteRequest.getApellidos());
		cliente.setCelular(clienteRequest.getCelular());
		cliente.setCorreo(clienteRequest.getCorreo());
		// No se actualiza el ID aquí, ya que es para un cliente existente
	}
}