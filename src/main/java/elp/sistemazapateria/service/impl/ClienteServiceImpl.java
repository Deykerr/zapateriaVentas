package elp.sistemazapateria.service.impl;

import elp.sistemazapateria.controller.dto.ClienteRequest;
import elp.sistemazapateria.controller.dto.ClienteResponse;
import elp.sistemazapateria.model.Cliente;
import elp.sistemazapateria.repository.ClienteRepository;
import elp.sistemazapateria.service.ClienteService;
import elp.sistemazapateria.service.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    /**
     * Metodo que trae toda la collection de cliente de la BD y lo pasa por el mapper
     * para responder como clienteResponse.
     */

    @Override
    public Collection<ClienteResponse> findAllCliente() {
        Collection<Cliente> listClienteResponses = clienteRepository.findAll();
        return clienteMapper.toListClienteToClienteResponse(listClienteResponses);
    }

    @Override
    public ClienteResponse findByIdCliente(Long idCliente) {
        // buscar al cliente por id del cliente usando repocitory y devolver
        //el response usando mapper, osea crear un nuevo mapper
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        return clienteMapper.toClienteToClienteResponse(cliente);
    }

    //Crear Cliente
    @Override
    public void saveCliente(ClienteRequest request) {
        Cliente clienteNew =new  Cliente();
        System.out.println("que llega" + clienteNew);
        clienteNew.setNombre(request.getNombre());
        clienteNew.setApellidos(request.getApellidos());
        clienteNew.setCelular(request.getCelular());
        clienteNew.setCorreoElectronico(request.getCorreoElectronico());
        clienteNew.setDireccion(request.getDireccion());
        clienteNew.setCompras(new ArrayList<>());
        clienteRepository.save(clienteNew);
    }

    @Override
    public void updateCliente(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setNombre(request.getNombre());
            cliente.setApellidos(request.getApellidos());
            cliente.setCelular(request.getCelular());
            cliente.setCorreoElectronico(request.getCorreoElectronico());
            cliente.setDireccion(request.getDireccion());
            cliente.setCompras(new ArrayList<>());
            clienteRepository.save(cliente);
        }
    }

    @Override
    public void deleteCliente(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if (cliente != null) {
            clienteRepository.delete(cliente);
        }
    }
}

