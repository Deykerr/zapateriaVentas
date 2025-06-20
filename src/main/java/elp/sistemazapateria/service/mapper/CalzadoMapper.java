package elp.sistemazapateria.service.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import elp.sistemazapateria.controller.dto.CalzadoRequest;
import elp.sistemazapateria.controller.dto.CalzadoResponse;
import elp.sistemazapateria.model.Calzado;

@Component
public class CalzadoMapper {
	
    public Collection<CalzadoResponse> toListCalzadoToCalzadoResponse(Collection<Calzado> listCalzado) {
        Collection<CalzadoResponse> listCalzadoResponses = new ArrayList<>();

        if (listCalzado != null && !listCalzado.isEmpty()) {
            for (Calzado calzado : listCalzado) {
               
                listCalzadoResponses.add(toCalzadoToCalzadoResponse(calzado));
            }
        }
        return listCalzadoResponses;
    }

    
    public CalzadoResponse toCalzadoToCalzadoResponse(Calzado calzado) {
        CalzadoResponse calzadoResponse = new CalzadoResponse();
        if (calzado != null) {
            calzadoResponse.setIdCalzado(calzado.getIdCalzado());
            calzadoResponse.setMarca(calzado.getMarca());
            calzadoResponse.setIdModelo(calzado.getIdModelo());
            calzadoResponse.setPrecioVenta(calzado.getPrecioVenta());
            calzadoResponse.setCantidadStock(calzado.getCantidadStock());
            calzadoResponse.setEstado(calzado.getEstado());

           
            if (calzado.getModelo() != null) {
                calzadoResponse.setDescripcionModelo(calzado.getModelo().getDescripcion());
            }
        }
        return calzadoResponse;
    }

   
    public Calzado toCalzado(CalzadoRequest request) {
        if (request == null) {
            return null;
        }
        Calzado calzado = new Calzado();
        calzado.setMarca(request.getMarca());
        calzado.setIdModelo(request.getIdModelo()); 
        calzado.setPrecioVenta(request.getPrecioVenta());
        calzado.setCantidadStock(request.getCantidadStock());
        calzado.setEstado(request.getEstado());
     
        return calzado;
    }

  
    
    public void updateCalzadoFromDto(CalzadoRequest request, Calzado calzado) {
        if (request == null || calzado == null) {
            return;
        }
        calzado.setMarca(request.getMarca());
        calzado.setIdModelo(request.getIdModelo()); 
        calzado.setPrecioVenta(request.getPrecioVenta());
        calzado.setCantidadStock(request.getCantidadStock());
        calzado.setEstado(request.getEstado());
       
    }
}