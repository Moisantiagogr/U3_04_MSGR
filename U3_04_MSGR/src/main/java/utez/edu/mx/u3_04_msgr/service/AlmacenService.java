package utez.edu.mx.u3_04_msgr.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import utez.edu.mx.u3_04_msgr.model.Almacen;
import utez.edu.mx.u3_04_msgr.model.Cliente;
import utez.edu.mx.u3_04_msgr.model.EstadoAlmacen;
import utez.edu.mx.u3_04_msgr.repository.AlmacenRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlmacenService {
    private static  final Logger logger = LogManager.getLogger(AlmacenService.class);
    private final AlmacenRepository almacenRepository;
    private final ClaveGeneratorService claveGeneratorService;

    public AlmacenService(AlmacenRepository almacenRepository, ClaveGeneratorService claveGeneratorService) {
        this.almacenRepository = almacenRepository;
        this.claveGeneratorService = claveGeneratorService;
    }

    public Almacen crear(Almacen almacen){
        logger.info("Creando Alamcen");
        almacen.setFechaRegistro(LocalDate.now());
        almacen.setEstado(EstadoAlmacen.DISPONIBLE);

        Almacen almacenSaved = almacenRepository.save(almacen);
        almacenSaved.setClave(claveGeneratorService.generarClaveAlmacen(almacen.getCede().getClave(), almacenSaved.getId()));
    return almacenRepository.save(almacenSaved);
    }

    public List<Almacen> obtenerTodos() {
        logger.info("Obteniendo todos los almacenes");
        return almacenRepository.findAll();
    }

    public Almacen getbyId(Long id){
        logger.info("Buscando almacen por ID: {}", id);
        return  almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacen no encontrado con ID: " + id));
    }

    public Almacen actualizar(Long id, Almacen almacen){
        logger.info("Actualizando alamcen con ID: {}", id);
        Almacen existente = getbyId(id);

        existente.setPrecioVenta(almacen.getPrecioVenta());
        existente.setPrecioRenta(almacen.getPrecioRenta());
        existente.setTamano(almacen.getTamano());

        return almacenRepository.save(existente);
    }

    public void eliminar(Long id){
        logger.info("Eliminando almacen con ID: {}", id);
        if (!almacenRepository.existsById(id)) {
            logger.error("Almacen no encontrado con ID: {}", id);
            throw new RuntimeException("Almacen no encontrado con ID: " + id);
        }
        almacenRepository.deleteById(id);
    }


public Almacen cambiarEstado(Long id, EstadoAlmacen estado, Cliente cliente) {
    logger.info("Cambiando estado de almacén ID: {} a {}", id, estado);
    Almacen almacen = getbyId(id);

    if (almacen.getEstado() != EstadoAlmacen.DISPONIBLE) {
        throw new IllegalStateException("El almacén no está disponible");
    }

    almacen.setEstado(estado);
    almacen.setCliente(cliente);
    return almacenRepository.save(almacen);
}
}
