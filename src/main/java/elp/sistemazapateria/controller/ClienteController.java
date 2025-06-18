package elp.sistemazapateria.controller;


import elp.sistemazapateria.controller.dto.ClienteRequest;
import elp.sistemazapateria.controller.dto.ClienteResponse;
import elp.sistemazapateria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<Collection<ClienteResponse>> getClientes(){
        return  ResponseEntity.ok(clienteService.findAllCliente());
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteResponse> getClientesById(@PathVariable Long id){
        return  ResponseEntity.ok(clienteService.findByIdCliente(id));
    }

    @PostMapping("/save/cliente")
    public void saveClientesById(@RequestBody ClienteRequest request){
        clienteService.saveCliente(request);
    }

    @PutMapping("/update/cliente/{id}")
    public void updateClientesById(@PathVariable Long id, @RequestBody ClienteRequest request){
        clienteService.updateCliente(id, request);
    }

    @PutMapping("/delete/cliente/{id}")
    public void deleteClientesById(@PathVariable Long id){
        clienteService.deleteCliente(id);
    }
}
