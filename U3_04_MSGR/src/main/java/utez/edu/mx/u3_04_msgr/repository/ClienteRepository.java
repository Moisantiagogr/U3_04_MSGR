package utez.edu.mx.u3_04_msgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.u3_04_msgr.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    boolean existsByCorreo(String correo);
    boolean existsByTelefono(String telefono);
}
