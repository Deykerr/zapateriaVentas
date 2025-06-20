package elp.sistemazapateria.service.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import elp.sistemazapateria.controller.dto.ModeloRequest;
import elp.sistemazapateria.controller.dto.ModeloResponse;
import elp.sistemazapateria.model.Modelo;

@Component
public class ModeloMapper {
	  /**
     * Convierte una colección de entidades Modelo a una colección de ModeloResponse DTOs.
     * @param listModelo La colección de entidades Modelo.
     * @return Una colección de ModeloResponse DTOs.
     */
    public Collection<ModeloResponse> toListModeloToModeloResponse(Collection<Modelo> listModelo) {
        Collection<ModeloResponse> listModeloResponses = new ArrayList<>();

        if (listModelo != null && !listModelo.isEmpty()) {
            for (Modelo modelo : listModelo) {
                // Llama al método para mapear un solo modelo
                listModeloResponses.add(toModeloToModeloResponse(modelo));
            }
        }
        return listModeloResponses;
    }

    /**
     * Convierte una entidad Modelo a un ModeloResponse DTO.
     * @param modelo La entidad Modelo.
     * @return El ModeloResponse DTO.
     */
    public ModeloResponse toModeloToModeloResponse(Modelo modelo) {
        ModeloResponse modeloResponse = new ModeloResponse();
        if (modelo != null) {
            modeloResponse.setIdModelo(modelo.getIdModelo());
            modeloResponse.setDescripcion(modelo.getDescripcion());
            modeloResponse.setEstado(modelo.getEstado());
        }
        return modeloResponse;
    }

    /**
     * Convierte un ModeloRequest DTO a una entidad Modelo.
     * Utilizado para crear nuevos modelos.
     * @param request El ModeloRequest DTO.
     * @return La entidad Modelo.
     */
    public Modelo toModelo(ModeloRequest request) {
        if (request == null) {
            return null;
        }
        Modelo modelo = new Modelo();
        modelo.setDescripcion(request.getDescripcion());
        modelo.setEstado(request.getEstado());
        // No se toca la relación 'calzados' aquí, se gestiona desde Calzado
        return modelo;
    }

    /**
     * Actualiza los campos de una entidad Modelo existente con los datos de un ModeloRequest DTO.
     * Utilizado para actualizar modelos.
     * @param request El ModeloRequest DTO con los datos actualizados.
     * @param modelo La entidad Modelo existente a actualizar.
     */
    public void updateModeloFromDto(ModeloRequest request, Modelo modelo) {
        if (request == null || modelo == null) {
            return;
        }
        modelo.setDescripcion(request.getDescripcion());
        modelo.setEstado(request.getEstado());
        // No se toca el idModelo (es la PK)
        // No se toca la relación 'calzados' aquí, para evitar sobrescribir
    }
}
