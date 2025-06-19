package elp.sistemazapateria.service;

import java.util.Collection;

import elp.sistemazapateria.controller.dto.CalzadoRequest;
import elp.sistemazapateria.controller.dto.CalzadoResponse;

public interface CalzadoService {

    Collection<CalzadoResponse> findAllCalzado();

    
    CalzadoResponse findByIdCalzado(Integer idCalzado);

    void saveCalzado(CalzadoRequest request);

  
    void updateCalzado(Integer idCalzado, CalzadoRequest request);

    
    void deleteCalzado(Integer idCalzado);
}