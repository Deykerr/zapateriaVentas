package elp.sistemazapateria.controller;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elp.sistemazapateria.controller.dto.ClienteRequest;
import elp.sistemazapateria.controller.dto.ClienteResponse;
import elp.sistemazapateria.service.ClienteService;


@RestController
@RequestMapping("v1/clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    /**
     * Obtiene todos los clientes.
     * GET /v1/clientes
     * @return Colección de ClienteResponse.
     */
    @GetMapping // URI queda como /v1/clientes
    public ResponseEntity<Collection<ClienteResponse>> getClientes(){
        return  ResponseEntity.ok(clienteService.findAllCliente());
    }

    /**
     * Obtiene un cliente por su ID.
     * GET /v1/clientes/{id}
     * @param id El ID del cliente.
     * @return ClienteResponse si se encuentra, o 404 Not Found si no (si el servicio lanza excepción).
     */
    @GetMapping("/{id}") // URI queda como /v1/clientes/{id}
    public ResponseEntity<ClienteResponse> getClientesById(@PathVariable Long id){
        return  ResponseEntity.ok(clienteService.findByIdCliente(id));
    }

    /**
     * Crea un nuevo cliente.
     * POST /v1/clientes
     * @param request Los datos del cliente a crear.
     * @return 201 Created con el nuevo cliente y su ubicación.
     */
    @PostMapping // URI queda como /v1/clientes
    public void saveClientesById(@RequestBody ClienteRequest request){
        clienteService.saveCliente(request);
    }

    /**
     * Actualiza un cliente existente por su ID.
     * PUT /v1/clientes/{id}
     * @param id El ID del cliente a actualizar.
     * @param request Los datos actualizados del cliente.
     * @return 200 OK con el cliente actualizado, o 404 Not Found.
     */
    @PutMapping("/{id}") // URI queda como /v1/clientes/{id}
    public void updateClientesById(@PathVariable Long id, @RequestBody ClienteRequest request){
        clienteService.updateCliente(id, request);
    }

    /**
     * Elimina un cliente por su ID.
     * DELETE /v1/clientes/{id}
     * @param id El ID del cliente a eliminar.
     * @return 204 No Content si la eliminación es exitosa, o 404 Not Found.
     */
    @DeleteMapping("/{id}") // ¡Importante: Usar @DeleteMapping! URI queda como /v1/clientes/{id}
    public void deleteClientesById(@PathVariable Long id){
        clienteService.deleteCliente(id);
    }
}
