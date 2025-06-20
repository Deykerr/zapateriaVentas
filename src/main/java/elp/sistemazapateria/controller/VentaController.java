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

import elp.sistemazapateria.controller.dto.VentaRequest;
import elp.sistemazapateria.controller.dto.VentaResponse;
import elp.sistemazapateria.service.VentaService;

@RestController
@RequestMapping("v1/ventas") // URI base para ventas
public class VentaController {
	@Autowired
	private VentaService ventaService;

	
	@GetMapping
	public ResponseEntity<Collection<VentaResponse>> getAllVentas() {
		return ResponseEntity.ok(ventaService.findAllVenta());
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<VentaResponse> getVentaById(@PathVariable Integer id) {
	
		return ResponseEntity.ok(ventaService.findByIdVenta(id));
	}

	
	@PostMapping
	public ResponseEntity<Void> createVenta(@RequestBody VentaRequest request) {
		ventaService.saveVenta(request);
		return ResponseEntity.status(201).build(); // 201 Created
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateVenta(@PathVariable Integer id, @RequestBody VentaRequest request) {
		ventaService.updateVenta(id, request);
		return ResponseEntity.ok().build(); // 200 OK
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVenta(@PathVariable Integer id) {
		ventaService.deleteVenta(id);
		return ResponseEntity.noContent().build(); // 204 No Content
	}
}
