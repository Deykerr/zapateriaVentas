package elp.sistemazapateria.service.mapper;

import elp.sistemazapateria.controller.dto.CalzadoRequest;
import elp.sistemazapateria.controller.dto.CalzadoResponse;
import elp.sistemazapateria.model.Calzado;

import org.springframework.stereotype.Component; // Para que Spring pueda inyectarlo

import java.util.Collection;
import java.util.stream.Collectors;

@Component // Anotación para que Spring la reconozca como un componente y pueda ser inyectada
public class CalzadoMapper {

    // Método para convertir una entidad Calzado a CalzadoResponse
    public CalzadoResponse toCalzadoToCalzadoResponse(Calzado calzado) {
        if (calzado == null) {
            return null;
        }
        CalzadoResponse response = new CalzadoResponse();
        response.setIdCalzado(calzado.getIdCalzado());
        response.setNombre(calzado.getIdNombre());
        response.setIdmodelo(calzado.getIdmodelo());
        response.setPrecioVenta(calzado.getPrecioVenta());
        response.setCantidadStock(calzado.getCantidadStock());
        return response;
    }

    // Método para convertir una colección de entidades Calzado a una colección de CalzadoResponse
    public Collection<CalzadoResponse> toListCalzadoToCalzadoResponse(Collection<Calzado> calzados) {
        if (calzados == null) {
            return null;
        }
        return calzados.stream()
                .map(this::toCalzadoToCalzadoResponse)
                .collect(Collectors.toList());
    }

    // Opcional: Método para convertir CalzadoRequest a Calzado (útil para save/update, pero a menudo se hace directamente en el servicio)
    public Calzado toCalzadoRequestToCalzado(CalzadoRequest request) {
        if (request == null) {
            return null;
        }
        Calzado calzado = new Calzado();
        calzado.setNombre(request.getNombre());
        calzado.setIdmodelo(request.getIdmodelo());
        calzado.setPrecioVenta(request.getPrecioVenta());
        calzado.setCantidadStock(request.getCantidadStock());
        return calzado;
    }
}