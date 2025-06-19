package elp.sistemazapateria.service.mapper;

import elp.sistemazapateria.controller.dto.ClienteResponse;
import elp.sistemazapateria.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ClienteMapper {

    public Collection<ClienteResponse> toListClienteToClienteResponse(Collection<Cliente> listCliente) {
        Collection<ClienteResponse> listClienteResponses = new ArrayList<>();

        if(listCliente != null && !listCliente.isEmpty()) {
            for (Cliente cliente : listCliente) {
                ClienteResponse clienteResponse = new ClienteResponse();
                clienteResponse.setIdentificador(cliente.getId());
                clienteResponse.setNombre(cliente.getNombre());
                clienteResponse.setApellidos(cliente.getApellidos());
                clienteResponse.setCelular(cliente.getCelular());
                clienteResponse.setCorreoElectronico(cliente.getCorreoElectronico());
                listClienteResponses.add(clienteResponse);
            }
        }

        return listClienteResponses;
    }

    public ClienteResponse toClienteToClienteResponse(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();
        if(cliente != null) {
            clienteResponse.setNombre(cliente.getNombre());
            clienteResponse.setApellidos(cliente.getApellidos());
            clienteResponse.setCelular(cliente.getCelular());
            clienteResponse.setCorreoElectronico(cliente.getCorreoElectronico());

        }
        return clienteResponse;
    }

}
