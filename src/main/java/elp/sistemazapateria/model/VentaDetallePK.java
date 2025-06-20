package elp.sistemazapateria.model;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;


@Data
@Embeddable
public class VentaDetallePK implements Serializable{
	@Column(name = "id_venta")
	private Integer idVenta;
	
	@Column(name = "id_calzado")
	private Integer idCalzado;
	
	private static final long serialVersionUID = 1L;
}
