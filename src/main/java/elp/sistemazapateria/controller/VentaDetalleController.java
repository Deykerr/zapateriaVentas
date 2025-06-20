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

import elp.sistemazapateria.controller.dto.VentaDetalleRequest;
import elp.sistemazapateria.controller.dto.VentaDetalleResponse;
import elp.sistemazapateria.model.VentaDetallePK; // Necesario para la clave compuesta
import elp.sistemazapateria.service.VentaDetalleService;

@RestController
@RequestMapping("api/venta-detalles") // URI base para detalles de venta (usando guion)
public class VentaDetalleController {
	@Autowired
	private VentaDetalleService ventaDetalleService;


	@GetMapping
	public ResponseEntity<Collection<VentaDetalleResponse>> getAllVentaDetalles() {
		return ResponseEntity.ok(ventaDetalleService.findAllVentaDetalle());
	}


	@GetMapping("/{idVenta}/{idCalzado}")
	public ResponseEntity<VentaDetalleResponse> getVentaDetalleById(@PathVariable Integer idVenta,
			@PathVariable Integer idCalzado) {
		// Construimos la clave primaria compuesta
		VentaDetallePK pk = new VentaDetallePK();
		pk.setIdVenta(idVenta);
		pk.setIdCalzado(idCalzado);
		// Asumiendo que findByIdVentaDetalle devuelve null si no encuentra
		return ResponseEntity.ok(ventaDetalleService.findByIdVentaDetalle(pk));
	}

	
	@PostMapping
	public ResponseEntity<Void> createVentaDetalle(@RequestBody VentaDetalleRequest request) {
		ventaDetalleService.saveVentaDetalle(request);
		return ResponseEntity.status(201).build(); // 201 Created
	}


	@PutMapping("/{idVenta}/{idCalzado}")
	public ResponseEntity<Void> updateVentaDetalle(@PathVariable Integer idVenta, @PathVariable Integer idCalzado,
			@RequestBody VentaDetalleRequest request) {
		// Construimos la clave primaria compuesta
		VentaDetallePK pk = new VentaDetallePK();
		pk.setIdVenta(idVenta);
		pk.setIdCalzado(idCalzado);
		ventaDetalleService.updateVentaDetalle(pk, request);
		return ResponseEntity.ok().build(); // 200 OK
	}


	@DeleteMapping("/{idVenta}/{idCalzado}")
	public ResponseEntity<Void> deleteVentaDetalle(@PathVariable Integer idVenta, @PathVariable Integer idCalzado) {
		// Construimos la clave primaria compuesta
		VentaDetallePK pk = new VentaDetallePK();
		pk.setIdVenta(idVenta);
		pk.setIdCalzado(idCalzado);
		ventaDetalleService.deleteVentaDetalle(pk);
		return ResponseEntity.noContent().build(); // 204 No Content
	}
}
