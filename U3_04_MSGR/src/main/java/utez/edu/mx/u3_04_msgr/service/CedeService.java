package utez.edu.mx.u3_04_msgr.service;

import ch.qos.logback.classic.selector.servlet.LoggerContextFilter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import utez.edu.mx.u3_04_msgr.model.Cede;
import utez.edu.mx.u3_04_msgr.repository.CedeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CedeService {
    private static final Logger logger = LogManager.getLogger(CedeService.class);
    private final CedeRepository cedeRepository;
    private final ClaveGeneratorService claveGeneratorService;

    public CedeService(CedeRepository cedeRepository, ClaveGeneratorService claveGeneratorService) {
        this.cedeRepository = cedeRepository;
        this.claveGeneratorService = claveGeneratorService;
    }

    public Cede crear(Cede cede){
        logger.info("Creando nueva cede ");
        Cede cedeSaved = cedeRepository.save(cede);
        cedeSaved.setClave(claveGeneratorService.generarClaveCede(cedeSaved.getId()));
        return cedeRepository.save(cedeSaved);
    }

    public List<Cede> obtenerTodos() {
        logger.info("Obteniendo todas las sedes");
        return cedeRepository.findAll();
    }

    public Cede getById(Long id) {
        logger.info("Buscando cede por ID: {}", id);
        return cedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cede no encontrada con ID: " + id));
    }

    public Cede actualizar(Long id, Cede cede) {
        logger.info("Actualizando cede ID: {}", id);
        Cede existente = getById(id);

        existente.setEstado(cede.getEstado());
        existente.setMunicipio(cede.getMunicipio());

        return cedeRepository.save(existente);
    }


public void eliminar(Long id) {
    logger.info("Eliminando cede ID: {}", id);
    if (!cedeRepository.existsById(id)) {

        logger.error("Cede no encontrada con ID: {}", id);
        throw new RuntimeException("Cede no encontrada con ID: " + id);
    }
    cedeRepository.deleteById(id);
}
}