package utez.edu.mx.u3_04_msgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.u3_04_msgr.model.Cede;

import java.util.Optional;

public interface CedeRepository extends JpaRepository<Cede, Long> {
    Optional <Cede> findByClave(String clave);
    boolean existsByClave(String clave);
}
