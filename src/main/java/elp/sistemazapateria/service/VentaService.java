package elp.sistemazapateria.service;

import java.util.Collection;

import elp.sistemazapateria.controller.dto.VentaRequest;
import elp.sistemazapateria.controller.dto.VentaResponse;


public interface VentaService {
    // Para traer todas las ventas
    Collection<VentaResponse> findAllVenta();

    // Para buscar una venta por su ID
    VentaResponse findByIdVenta(Integer idVenta);

    // Para crear una nueva venta
    void saveVenta(VentaRequest request);

    // Para actualizar una venta existente
    void updateVenta(Integer id, VentaRequest request);

    // Para eliminar una venta por su ID
    void deleteVenta(Integer idVenta);
}


