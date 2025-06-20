package elp.sistemazapateria.service.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import elp.sistemazapateria.controller.dto.CalzadoRequest;
import elp.sistemazapateria.controller.dto.CalzadoResponse;
import elp.sistemazapateria.model.Calzado;

/**
 * Clase Mapper responsable de convertir entre entidades Calzado y sus DTOs (Objetos de Transferencia de Datos).
 * Esta clase está marcada como un Componente de Spring, lo que significa que puede ser detectada e
 * instanciada automáticamente por el escaneo de componentes de Spring.
 */
@Component
public class CalzadoMapper {
	
    /**
     * Convierte una colección de entidades Calzado a una colección de DTOs CalzadoResponse.
     * Itera a través de cada Calzado en la colección de entrada y lo convierte usando
     * el método toCalzadoToCalzadoResponse.
     * @param listCalzado La colección de entidades Calzado a convertir.
     * @return Una colección de DTOs CalzadoResponse.
     */
    public Collection<CalzadoResponse> toListCalzadoToCalzadoResponse(Collection<Calzado> listCalzado) {
        Collection<CalzadoResponse> listCalzadoResponses = new ArrayList<>();

        // Verifica si la colección de entrada no es nula y no está vacía
        if (listCalzado != null && !listCalzado.isEmpty()) {
            // Itera sobre cada entidad Calzado
            for (Calzado calzado : listCalzado) {
                // Convierte cada entidad Calzado a un DTO CalzadoResponse y lo añade a la lista
                listCalzadoResponses.add(toCalzadoToCalzadoResponse(calzado));
            }
        }
        return listCalzadoResponses;
    }

    /**
     * Convierte una sola entidad Calzado a un DTO CalzadoResponse.
     * Mapea las propiedades de la entidad Calzado a las propiedades correspondientes en CalzadoResponse.
     * También maneja objetos anidados como 'Modelo' para extraer información relevante (ej. descripción).
     * @param calzado La entidad Calzado a convertir.
     * @return Un DTO CalzadoResponse.
     */
    public CalzadoResponse toCalzadoToCalzadoResponse(Calzado calzado) {
        CalzadoResponse calzadoResponse = new CalzadoResponse();
        // Verifica si la entidad Calzado de entrada no es nula
        if (calzado != null) {
            // Mapea propiedades comunes
            calzadoResponse.setIdCalzado(calzado.getIdCalzado());
            calzadoResponse.setMarca(calzado.getMarca());
            calzadoResponse.setIdModelo(calzado.getIdModelo());
            calzadoResponse.setPrecioVenta(calzado.getPrecioVenta());
            calzadoResponse.setCantidadStock(calzado.getCantidadStock());
            calzadoResponse.setEstado(calzado.getEstado());

            // Verifica si el objeto Modelo asociado no es nulo antes de acceder a sus propiedades
            if (calzado.getModelo() != null) {
                calzadoResponse.setDescripcionModelo(calzado.getModelo().getDescripcion());
            }
        }
        return calzadoResponse;
    }

    /**
     * Convierte un DTO CalzadoRequest a una entidad Calzado.
     * Este método se usa típicamente al crear una nueva entidad Calzado a partir de los datos de una solicitud entrante.
     * @param request El DTO CalzadoRequest que contiene los datos para el nuevo Calzado.
     * @return Una entidad Calzado.
     */
    public Calzado toCalzado(CalzadoRequest request) {
        // Devuelve null si la solicitud de entrada es null
        if (request == null) {
            return null;
        }
        Calzado calzado = new Calzado();
        // Mapea las propiedades de CalzadoRequest a la entidad Calzado
        calzado.setMarca(request.getMarca());
        calzado.setIdModelo(request.getIdModelo()); 
        calzado.setPrecioVenta(request.getPrecioVenta());
        calzado.setCantidadStock(request.getCantidadStock());
        calzado.setEstado(request.getEstado());
       
        return calzado;
    }

    /**
     * Actualiza una entidad Calzado existente con datos de un DTO CalzadoRequest.
     * Este método se usa típicamente al modificar una entidad Calzado existente basada en los datos de una solicitud de actualización.
     * Asegura que solo se establezcan los campos actualizables, previniendo excepciones de puntero nulo
     * si la solicitud o el objeto calzado son nulos.
     * @param request El DTO CalzadoRequest que contiene los datos actualizados.
     * @param calzado La entidad Calzado existente a ser actualizada.
     */
    public void updateCalzadoFromDto(CalzadoRequest request, Calzado calzado) {
        // Retorna si la solicitud o el objeto calzado son nulos
        if (request == null || calzado == null) {
            return;
        }
        // Actualiza las propiedades de la entidad Calzado existente con los valores del DTO de solicitud
        calzado.setMarca(request.getMarca());
        calzado.setIdModelo(request.getIdModelo()); 
        calzado.setPrecioVenta(request.getPrecioVenta());
        calzado.setCantidadStock(request.getCantidadStock());
        calzado.setEstado(request.getEstado());
        
    }
}