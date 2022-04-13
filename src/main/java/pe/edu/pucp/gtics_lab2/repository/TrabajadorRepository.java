package pe.edu.pucp.gtics_lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.gtics_lab2.entity.Trabajador;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador,String> {
}
