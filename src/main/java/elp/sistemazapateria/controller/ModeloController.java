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

import elp.sistemazapateria.controller.dto.ModeloRequest;
import elp.sistemazapateria.controller.dto.ModeloResponse;
import elp.sistemazapateria.service.ModeloService;


@RestController
@RequestMapping("/api/modelos") // URI base para modelos
public class ModeloController {

	@Autowired
	private ModeloService modeloService;

	/**
	 * Obtiene todos los modelos. GET /v1/modelos
	 * 
	 * @return Colección de ModeloResponse.
	 */
	@GetMapping
	public ResponseEntity<Collection<ModeloResponse>> getAllModelos() {
		return ResponseEntity.ok(modeloService.findAllModelo());
	}

	/**
	 * Obtiene un modelo por su ID. GET /v1/modelos/{id}
	 * 
	 * @param id El ID del modelo.
	 * @return ModeloResponse si se encuentra, o 404 Not Found si no.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ModeloResponse> getModeloById(@PathVariable Integer id) {
		// Asumiendo que findByIdModelo devuelve null si no encuentra
		return ResponseEntity.ok(modeloService.findByIdModelo(id));
	}

	/**
	 * Crea un nuevo modelo. POST /v1/modelos
	 * 
	 * @param request Los datos del modelo a crear.
	 * @return 201 Created.
	 */
	@PostMapping
	public ResponseEntity<Void> createModelo(@RequestBody ModeloRequest request) {
		modeloService.saveModelo(request);
		return ResponseEntity.status(201).build(); // 201 Created
	}

	/**
	 * Actualiza un modelo existente por su ID. PUT /v1/modelos/{id}
	 * 
	 * @param id      El ID del modelo a actualizar.
	 * @param request Los datos actualizados del modelo.
	 * @return 200 OK.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateModelo(@PathVariable Integer id, @RequestBody ModeloRequest request) {
		modeloService.updateModelo(id, request);
		return ResponseEntity.ok().build(); // 200 OK
	}

	/**
	 * Elimina un modelo por su ID. DELETE /v1/modelos/{id}
	 * 
	 * @param id El ID del modelo a eliminar.
	 * @return 204 No Content.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteModelo(@PathVariable Integer id) {
		modeloService.deleteModelo(id);
		return ResponseEntity.noContent().build(); // 204 No Content
	}
}
