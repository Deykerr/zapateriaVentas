package elp.sistemazapateria.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elp.sistemazapateria.controller.dto.CalzadoRequest;
import elp.sistemazapateria.controller.dto.CalzadoResponse;
import elp.sistemazapateria.service.CalzadoService;


@RestController
@RequestMapping("/api/calzado") // La ruta base para este controlador //@RequestMapping("v1/calzados") 
public class CalzadoController {
	 @Autowired
	    private CalzadoService calzadoService;

	 	// --- Métodos de Acceso ---
	 
	    // Este endpoint puede ser accesible por cualquier usuario autenticado (USER, MOD, ADMIN)
	    @GetMapping // GET /api/calzado
	    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<Collection<CalzadoResponse>> getAllCalzados() {
	        return ResponseEntity.ok(calzadoService.findAllCalzado());
	    }

	    
	    @GetMapping("/{id}") // GET /api/calzado/{id}
	    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<CalzadoResponse> getCalzadoById(@PathVariable Integer id) {
	        
	        return ResponseEntity.ok(calzadoService.findByIdCalzado(id));
	    }

	   
	    // --- Métodos de Modificación (creación, actualización, eliminación) ---

	    // Solo los administradores o moderadores pueden añadir nuevos calzados
	    @PostMapping // POST /api/calzado
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<Void> createCalzado(@RequestBody CalzadoRequest request) {
	        calzadoService.saveCalzado(request);
	        return ResponseEntity.status(201).build(); 
	    }

	   
	    // Solo los administradores o moderadores pueden actualizar calzados
	    @PutMapping("/{id}") // PUT /api/calzado/{id}
	    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	    public ResponseEntity<Void> updateCalzado(@PathVariable Integer id, @RequestBody CalzadoRequest request) {
	        calzadoService.updateCalzado(id, request);
	        return ResponseEntity.ok().build(); 
	    }

	    
	    // Solo los administradores pueden eliminar calzados (más restrictivo)
	    @DeleteMapping("/{id}") // DELETE /api/calzado/{id}
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Void> deleteCalzado(@PathVariable Integer id) {
	        calzadoService.deleteCalzado(id);
	        return ResponseEntity.noContent().build(); 
	    }
}

