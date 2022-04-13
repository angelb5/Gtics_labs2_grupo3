package pe.edu.pucp.gtics_lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.pucp.gtics_lab2.entity.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
