package elp.sistemazapateria.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elp.sistemazapateria.controller.dto.CalzadoRequest;
import elp.sistemazapateria.controller.dto.CalzadoResponse;
import elp.sistemazapateria.model.Calzado;
import elp.sistemazapateria.repository.CalzadoRepository; 
import elp.sistemazapateria.service.CalzadoService;
import elp.sistemazapateria.service.mapper.CalzadoMapper; 


@Service
public class CalzadoServiceImpl implements CalzadoService {
	@Autowired
    private CalzadoRepository calzadoRepository;

    @Autowired
    private CalzadoMapper calzadoMapper;

   
    @Override
    public Collection<CalzadoResponse> findAllCalzado() {
        Collection<Calzado> listCalzados = calzadoRepository.findAll();
        return calzadoMapper.toListCalzadoToCalzadoResponse(listCalzados);
    }

    
    @Override
    public CalzadoResponse findByIdCalzado(Integer idCalzado) {
       
        Calzado calzado = calzadoRepository.findById(idCalzado).orElse(null);
        
        return calzadoMapper.toCalzadoToCalzadoResponse(calzado);
    }

   
    @Override
    public void saveCalzado(CalzadoRequest request) {
       
        Calzado calzadoNew = calzadoMapper.toCalzado(request);
        
        System.out.println("Calzado nuevo a guardar: " + calzadoNew);
        
        calzadoRepository.save(calzadoNew);
    }

  
    @Override
    public void updateCalzado(Integer id, CalzadoRequest request) {
       
        Calzado calzadoExistente = calzadoRepository.findById(id).orElse(null);

       
        if (calzadoExistente != null) {
            
            calzadoMapper.updateCalzadoFromDto(request, calzadoExistente);
            
            calzadoRepository.save(calzadoExistente);
        }
       
    }

    
    @Override
    public void deleteCalzado(Integer idCalzado) {
        
        Calzado calzado = calzadoRepository.findById(idCalzado).orElse(null);
        if (calzado != null) {
           
            calzadoRepository.delete(calzado);
        }
      
    }
}