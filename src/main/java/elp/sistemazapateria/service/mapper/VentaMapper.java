package elp.sistemazapateria.service.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import elp.sistemazapateria.controller.dto.VentaResponse;
import elp.sistemazapateria.model.Venta;



@Component
public class VentaMapper {
    public Collection<VentaResponse> toListVentaToVentaResponse(Collection<Venta> listVenta) {
        Collection<VentaResponse> listVentaResponses = new ArrayList<>();

        if(listVenta != null && !listVenta.isEmpty()) {
            for (Venta venta : listVenta) {
                VentaResponse ventaResponse = new VentaResponse();
                ventaResponse.setIdentificador(venta.getIdVenta());
                ventaResponse.setIdCliente(venta.getIdCliente());
                ventaResponse.setFecha(venta.getFecha());
                ventaResponse.setMetodoPago(venta.getMetodoPago());
                listVentaResponses.add(ventaResponse);
            }
        }

        return listVentaResponses;
    }

    public VentaResponse toVentaToVentaResponse(Venta venta) {
    	VentaResponse ventaResponse = new VentaResponse();
        if(venta != null) {
            ventaResponse.setIdentificador(venta.getIdVenta());
        	ventaResponse.setIdCliente(venta.getIdCliente());
        	ventaResponse.setFecha(venta.getFecha());
        	ventaResponse.setMetodoPago(venta.getMetodoPago());

        }
        return ventaResponse;
    }
}
