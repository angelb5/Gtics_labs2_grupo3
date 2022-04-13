package pe.edu.pucp.gtics_lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.gtics_lab2.entity.Trabajador;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador,String> {

    Optional<Trabajador> findByDni(String dni);

    void deleteByDni(String dni);

    @Query(nativeQuery = true, value="SELECT * FROM trabajadores where idsede = ?1")
    List<Trabajador> returnList(int id);
}
