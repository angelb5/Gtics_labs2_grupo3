package pe.edu.pucp.gtics_lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.gtics_lab2.entity.Sede;

import java.util.List;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
}
