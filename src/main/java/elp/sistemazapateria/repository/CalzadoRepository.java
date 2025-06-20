package elp.sistemazapateria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elp.sistemazapateria.model.Calzado;



@Repository
public interface CalzadoRepository extends JpaRepository<Calzado, Integer> {

}
