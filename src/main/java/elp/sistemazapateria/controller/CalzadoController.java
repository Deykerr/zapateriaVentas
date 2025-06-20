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

import elp.sistemazapateria.controller.dto.CalzadoRequest;
import elp.sistemazapateria.controller.dto.CalzadoResponse;
import elp.sistemazapateria.service.CalzadoService;


@RestController
@RequestMapping("v1/calzados") 
public class CalzadoController {
	 @Autowired
	    private CalzadoService calzadoService;

	    
	    @GetMapping
	    public ResponseEntity<Collection<CalzadoResponse>> getAllCalzados() {
	        return ResponseEntity.ok(calzadoService.findAllCalzado());
	    }

	    
	    @GetMapping("/{id}")
	    public ResponseEntity<CalzadoResponse> getCalzadoById(@PathVariable Integer id) {
	        
	        return ResponseEntity.ok(calzadoService.findByIdCalzado(id));
	    }

	   
	    @PostMapping
	    public ResponseEntity<Void> createCalzado(@RequestBody CalzadoRequest request) {
	        calzadoService.saveCalzado(request);
	        return ResponseEntity.status(201).build(); 
	    }

	   
	    @PutMapping("/{id}")
	    public ResponseEntity<Void> updateCalzado(@PathVariable Integer id, @RequestBody CalzadoRequest request) {
	        calzadoService.updateCalzado(id, request);
	        return ResponseEntity.ok().build(); 
	    }

	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCalzado(@PathVariable Integer id) {
	        calzadoService.deleteCalzado(id);
	        return ResponseEntity.noContent().build(); 
	    }
}

