package elp.sistemazapateria.controller;

import java.util.Collection;  
import elp.sistemazapateria.controller.dto.CalzadoRequest; 
import elp.sistemazapateria.controller.dto.CalzadoResponse; 
import elp.sistemazapateria.service.CalzadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/calzado")
public class CalzadoController {

    @Autowired
    CalzadoService calzadoService;

    @GetMapping("/calzados")
    public ResponseEntity<Collection<CalzadoResponse>> getCalzados(){
        // calzadoService.findAllCalzado() 
        return ResponseEntity.ok(calzadoService.findAllCalzado());
    }

    @GetMapping("/calzado/{id}")
    public ResponseEntity<CalzadoResponse> getCalzadoById(@PathVariable Integer id){ 
        return ResponseEntity.ok(calzadoService.findByIdCalzado(id));
    }

    @PostMapping("/save/calzado")
    public void saveCalzado(@RequestBody CalzadoRequest request){
        calzadoService.saveCalzado(request);
    }

    @PutMapping("/update/calzado/{id}")
    public void updateCalzado(@PathVariable Integer id, @RequestBody CalzadoRequest request){ 
        calzadoService.updateCalzado(id, request);
    }

    @DeleteMapping("/delete/calzado/{id}") 
    public void deleteCalzado(@PathVariable Integer id){ 
        calzadoService.deleteCalzado(id);
    }
}
