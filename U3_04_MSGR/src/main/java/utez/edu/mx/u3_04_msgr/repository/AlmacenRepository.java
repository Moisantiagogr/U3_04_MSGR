package utez.edu.mx.u3_04_msgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.u3_04_msgr.model.Almacen;

import java.util.Optional;

public interface AlmacenRepository  extends JpaRepository<Almacen,Long> {
    Optional<Almacen> findByClave(String clave);
    boolean existsByClave(String clave);
}
