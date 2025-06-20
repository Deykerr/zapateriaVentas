package elp.sistemazapateria.service;
import java.util.Collection;

import elp.sistemazapateria.controller.dto.CalzadoRequest;
import elp.sistemazapateria.controller.dto.CalzadoResponse;

public interface CalzadoService {
	   // Para traer todos los calzados
    Collection<CalzadoResponse> findAllCalzado();

    // Para buscar un calzado por su ID
    CalzadoResponse findByIdCalzado(Integer idCalzado);

    // Para crear un nuevo calzado
    void saveCalzado(CalzadoRequest request);

    // Para actualizar un calzado existente
    void updateCalzado(Integer id, CalzadoRequest request);

    // Para eliminar un calzado por su ID
    void deleteCalzado(Integer idCalzado);
}
