package pe.edu.pucp.gtics_lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.gtics_lab2.entity.Trabajador;

import java.util.List;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador,String> {

    @Query(nativeQuery = true, value="SELECT * FROM trabajadores where idsede = ?1")
    List<Trabajador> returnList(int id);
}
