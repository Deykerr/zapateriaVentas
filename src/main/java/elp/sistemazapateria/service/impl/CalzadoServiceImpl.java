package elp.sistemazapateria.service.impl;

import elp.sistemazapateria.controller.dto.CalzadoRequest;
import elp.sistemazapateria.controller.dto.CalzadoResponse;
import elp.sistemazapateria.model.Calzado; // Importa tu entidad Calzado
import elp.sistemazapateria.repository.CalzadoRepository; // Importa tu CalzadoRepository
import elp.sistemazapateria.service.CalzadoService; // Importa la interfaz CalzadoService
import elp.sistemazapateria.service.mapper.CalzadoMapper; // Importa tu CalzadoMapper (lo crearemos después)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional; // Necesario para .orElse()

@Service // Anotación para que Spring la reconozca como un servicio
public class CalzadoServiceImpl implements CalzadoService { // Implementa la interfaz CalzadoService

    @Autowired
    private CalzadoRepository calzadoRepository;

    @Autowired
    private CalzadoMapper calzadoMapper; // Se inyectará el Mapper

    /**
     * Metodo que trae toda la collection de calzados de la BD y lo pasa por el mapper
     * para responder como CalzadoResponse.
     */
    @Override
    public Collection<CalzadoResponse> findAllCalzado() {
        Collection<Calzado> listCalzados = calzadoRepository.findAll();
        // Asumiendo que CalzadoMapper tiene un método toListCalzadoToCalzadoResponse
        return calzadoMapper.toListCalzadoToCalzadoResponse(listCalzados);
    }

    @Override
    public CalzadoResponse findByIdCalzado(Integer idCalzado) { // El ID es Integer
        Optional<Calzado> calzadoOptional = calzadoRepository.findById(idCalzado);
        Calzado calzado = calzadoOptional.orElse(null); // Manejo de si no se encuentra
        // Asumiendo que CalzadoMapper tiene un método toCalzadoToCalzadoResponse
        return calzadoMapper.toCalzadoToCalzadoResponse(calzado);
    }

    // Crear Calzado
    @Override
    public void saveCalzado(CalzadoRequest request) {
        Calzado calzadoNew = new Calzado();
        // Mapea los campos de CalzadoRequest a la entidad Calzado
        calzadoNew.setNombre(request.getNombre());
        calzadoNew.setIdmodelo(request.getIdmodelo()); // Si usas el ID directo
        calzadoNew.setPrecioVenta(request.getPrecioVenta());
        calzadoNew.setCantidadStock(request.getCantidadStock());

        

        calzadoRepository.save(calzadoNew);
    }

    @Override
    public void updateCalzado(Integer id, CalzadoRequest request) { // El ID es Integer
        Optional<Calzado> calzadoOptional = calzadoRepository.findById(id);
        if (calzadoOptional.isPresent()) {
            Calzado calzado = calzadoOptional.get();
            // Actualiza los campos de la entidad con los datos del request
            calzado.setNombre(request.getNombre());
            calzado.setIdmodelo(request.getIdmodelo()); // Si usas el ID directo
            calzado.setPrecioVenta(request.getPrecioVenta());
            calzado.setCantidadStock(request.getCantidadStock());

           

            calzadoRepository.save(calzado);
        }
    }

    @Override
    public void deleteCalzado(Integer idCalzado) { // El ID es Integer
        Optional<Calzado> calzadoOptional = calzadoRepository.findById(idCalzado);
        if (calzadoOptional.isPresent()) {
            calzadoRepository.delete(calzadoOptional.get());
        }
    }
}