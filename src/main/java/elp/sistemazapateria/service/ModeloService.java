package elp.sistemazapateria.service;

import java.util.Collection;

import elp.sistemazapateria.controller.dto.ModeloRequest;
import elp.sistemazapateria.controller.dto.ModeloResponse;

public interface ModeloService {
	   // Para traer todos los modelos
    Collection<ModeloResponse> findAllModelo();

    // Para buscar un modelo por su ID
    ModeloResponse findByIdModelo(Integer idModelo);

    // Para crear un nuevo modelo
    void saveModelo(ModeloRequest request);

    // Para actualizar un modelo existente
    void updateModelo(Integer id, ModeloRequest request);

    // Para eliminar un modelo por su ID
    void deleteModelo(Integer idModelo);
}
