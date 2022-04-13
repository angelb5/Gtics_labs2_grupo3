package pe.edu.pucp.gtics_lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.pucp.gtics_lab2.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}
