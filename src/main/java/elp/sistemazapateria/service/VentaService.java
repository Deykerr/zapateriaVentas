package elp.sistemazapateria.service;

import java.util.Collection;



public interface VentaService {
	//Para Traer todos los clientes
	Collection<VentaResponse> findAllVenta ();
	
	//Para buscar por id de la venta
	VentaResponse findByIdVenta(Integer idVenta);
	//Para crear venta
	void saveVenta (VentaRequest request);
	//Para Actualizar venta
	void updateVenta(Integer id, VentaRequest request);
	
	//void deleteVenta (Long idVenta);

}

