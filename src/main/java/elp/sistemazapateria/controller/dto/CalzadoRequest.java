package elp.sistemazapateria.controller.dto;


import lombok.Getter;

import lombok.Setter;

@Getter
@Setter

public class CalzadoRequest {

  
    private String nombre;

 
    private Integer idmodelo; 

 
    private Double precioVenta; 

    private Integer cantidadStock;
    }