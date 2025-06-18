package elp.sistemazapateria.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elp.sistemazapateria.controller.dto.VentaRequest;
import elp.sistemazapateria.controller.dto.VentaResponse;
import elp.sistemazapateria.model.Venta;
import elp.sistemazapateria.repository.VentaRepository;
import elp.sistemazapateria.service.VentaService;
import elp.sistemazapateria.service.mapper.VentaMapper;



@Service
public class VentaServiceImpl implements VentaService {
	 @Autowired
	    private VentaRepository ventaRepository;

	    @Autowired
	    private VentaMapper ventaMapper;

	    /**
	     * Metodo que trae toda la collection de venta de la BD y lo pasa por el mapper
	     * para responder como clienteResponse.
	     */

	    @Override
	    public Collection<VentaResponse> findAllVenta() {
	        Collection<Venta> listVentaResponses = ventaRepository.findAll();
	        return ventaMapper.toListVentaToVentaResponse(listVentaResponses);
	    }

	    @Override
	    public VentaResponse findByIdVenta(Integer idVenta) {
	        // buscar la venta por id dela venta usando repocitory y devolver
	        //el response usando mapper, osea crear un nuevo mapper
	        Venta venta = ventaRepository.findById(idVenta).orElse(null);
	        return ventaMapper.toVentaToVentaResponse(venta);
	    }

	    //Crear venta
	    @Override
	    public void saveVenta(VentaRequest request) {
	        Venta ventaNew =new  Venta();
	        System.out.println("que llega" + ventaNew);
	        ventaNew.setIdCliente(request.getIdCliente());
	        //ventaNew.setFecha(request.getFecha());
	        ventaNew.setFecha(LocalDateTime.now());
	        ventaNew.setMetodoPago(request.getMetodoPago());
	        
	        ventaRepository.save(ventaNew);
	    }

	    @Override
	    public void updateVenta(Integer idVenta, VentaRequest request) {
	        Venta venta = ventaRepository.findById(idVenta).orElse(null);
	        if (venta != null) {
	        	venta.setIdCliente(request.getIdCliente());
	        	venta.setFecha(LocalDateTime.now());
	        	venta.setMetodoPago(request.getMetodoPago());	         
	            ventaRepository.save(venta);
	        }
	    }

	   /* @Override
	    public void deleteCliente(Integer idCliente) {
	        Cliente cliente = ventaRepository.findById(idCliente).orElse(null);
	        if (cliente != null) {
	        	ventaRepository.delete(cliente);
	        }
	    }*/
}
