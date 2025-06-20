package elp.sistemazapateria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import elp.sistemazapateria.model.VentaDetalle;
import elp.sistemazapateria.model.VentaDetallePK;

@Repository
public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, VentaDetallePK> {

}

