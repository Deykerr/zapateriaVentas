package elp.sistemazapateria.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elp.sistemazapateria.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

}

