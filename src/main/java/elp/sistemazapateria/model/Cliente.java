
package elp.sistemazapateria.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="clientes")
@Data
public class Cliente {

	 	@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nombre;
	    private String apellidos;
	    private Long celular;
	    private String correo;

	    
	    @OneToMany(mappedBy = "cliente")
	    private List<Venta> ventas;

}