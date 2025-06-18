package elp.sistemazapateria.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elp.sistemazapateria.controller.dto.VentaRequest;
import elp.sistemazapateria.controller.dto.VentaResponse;
import elp.sistemazapateria.service.VentaService;



@RestController
@RequestMapping("v1/venta")
public class VentaController {
    @Autowired
    VentaService ventaService;

    @GetMapping("/ventas")
    public ResponseEntity<Collection<VentaResponse>> getVentas(){
        return  ResponseEntity.ok(ventaService.findAllVenta());
    }

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<VentaResponse> getVentasById(@PathVariable Integer idVenta){
        return  ResponseEntity.ok(ventaService.findByIdVenta(idVenta));
    }

    @PostMapping("/save/venta")
    public void saveVentasById(@RequestBody VentaRequest request){
        ventaService.saveVenta(request);
    }

    @PutMapping("/update/venta/{idVenta}")
    public void updateVentasById(@PathVariable Integer idVenta, @RequestBody VentaRequest request){
    	ventaService.updateVenta(idVenta, request);
    }

    /*@PutMapping("/delete/venta/{id}")
    public void deleteVentasById(@PathVariable Integer id){
    	ventaService.deleteVenta(idVenta);
    }*/
}
