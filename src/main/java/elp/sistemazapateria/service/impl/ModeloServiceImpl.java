package elp.sistemazapateria.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elp.sistemazapateria.controller.dto.ModeloRequest;
import elp.sistemazapateria.controller.dto.ModeloResponse;
import elp.sistemazapateria.model.Modelo;
import elp.sistemazapateria.repository.ModeloRepository; // Asegúrate de tener esta interfaz
import elp.sistemazapateria.service.ModeloService;
import elp.sistemazapateria.service.mapper.ModeloMapper; // Asegúrate de tener este mapper


@Service
public class ModeloServiceImpl implements ModeloService{
	@Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private ModeloMapper modeloMapper;

    /**
     * Método que trae toda la colección de modelos de la BD y la mapea
     * para responder como ModeloResponse.
     */
    @Override
    public Collection<ModeloResponse> findAllModelo() {
        Collection<Modelo> listModelos = modeloRepository.findAll();
        return modeloMapper.toListModeloToModeloResponse(listModelos);
    }

    /**
     * Busca un modelo por su ID.
     * @param idModelo El ID del modelo a buscar.
     * @return El ModeloResponse DTO si se encuentra, o null si no.
     */
    @Override
    public ModeloResponse findByIdModelo(Integer idModelo) {
        // Busca el modelo por ID usando el repositorio.
        // .orElse(null) devolverá null si el modelo no se encuentra.
        Modelo modelo = modeloRepository.findById(idModelo).orElse(null);
        // Mapea la entidad Modelo encontrada a un ModeloResponse DTO.
        return modeloMapper.toModeloToModeloResponse(modelo);
    }

    /**
     * Guarda un nuevo modelo en la base de datos.
     * @param request El ModeloRequest DTO con los datos del nuevo modelo.
     */
    @Override
    public void saveModelo(ModeloRequest request) {
        // Usa el mapper para convertir el DTO de solicitud a la entidad Modelo
        Modelo modeloNew = modeloMapper.toModelo(request);
        // Línea de depuración: muestra el objeto Modelo antes de guardar
        System.out.println("Modelo nuevo a guardar: " + modeloNew);
        // Guarda el nuevo modelo en la base de datos
        modeloRepository.save(modeloNew);
    }

    /**
     * Actualiza un modelo existente en la base de datos.
     * @param id El ID del modelo a actualizar.
     * @param request El ModeloRequest DTO con los datos actualizados.
     */
    @Override
    public void updateModelo(Integer id, ModeloRequest request) {
        // Busca el modelo existente por su ID
        Modelo modeloExistente = modeloRepository.findById(id).orElse(null);

        // Si el modelo existe, actualiza sus campos con los datos del DTO
        if (modeloExistente != null) {
            // Usa el mapper para actualizar los campos del modelo existente
            modeloMapper.updateModeloFromDto(request, modeloExistente);
            // Guarda el modelo actualizado en la base de datos
            modeloRepository.save(modeloExistente);
        }
        // Nota: Si modeloExistente es null, no se hace nada.
    }

    /**
     * Elimina un modelo de la base de datos por su ID.
     * @param idModelo El ID del modelo a eliminar.
     */
    @Override
    public void deleteModelo(Integer idModelo) {
        // Busca el modelo para asegurarse de que existe antes de intentar eliminarlo
        Modelo modelo = modeloRepository.findById(idModelo).orElse(null);
        if (modelo != null) {
            // Elimina el modelo si fue encontrado
            modeloRepository.delete(modelo);
        }
        // Nota: Si modelo es null, no se hace nada.
    }
}
