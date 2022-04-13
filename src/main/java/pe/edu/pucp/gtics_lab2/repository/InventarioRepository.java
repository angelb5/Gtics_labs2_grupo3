package pe.edu.pucp.gtics_lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.gtics_lab2.entity.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Integer> {
}
