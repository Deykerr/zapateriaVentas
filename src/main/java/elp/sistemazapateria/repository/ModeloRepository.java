package elp.sistemazapateria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elp.sistemazapateria.model.Modelo;


@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer>{

}
