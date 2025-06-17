package utez.edu.mx.u3_04_msgr.service;


import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import utez.edu.mx.u3_04_msgr.model.Cliente;
import utez.edu.mx.u3_04_msgr.repository.ClienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private static final Logger logger = LogManager.getLogger(ClienteService.class);
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crear(Cliente cliente) {
        logger.info("Creando nuevo cliente");
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodos() {
        logger.info("Obteniendo todos los clientes");
        return clienteRepository.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        logger.info("Buscando cliente por ID: {}", id);
        return clienteRepository.findById(id)

            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente actualizar(Long id, Cliente cliente) {
        logger.info("Actualizando cliente ID: {}", id);
        Cliente existente = obtenerPorId(id);

        if (!existente.getTelefono().equals(cliente.getTelefono())) {
            validarTelefono(cliente.getTelefono());
        }
        if (!existente.getCorreo().equals(cliente.getCorreo())) {
            validarCorreo(cliente.getCorreo());
        }

        existente.setNombreCompleto(cliente.getNombreCompleto());
        existente.setTelefono(cliente.getTelefono());
        existente.setCorreo(cliente.getCorreo());

        return clienteRepository.save(existente);
    }

    public void eliminar(Long id) {
        logger.info("Eliminando cliente ID: {}", id);
        if (!clienteRepository.existsById(id)) {
            logger.error("Cliente no encontrado con ID: {}", id);

            throw new RuntimeException("Cliente no encontrado");
        }
        clienteRepository.deleteById(id);
    }

    private void validarCliente(Cliente cliente) {
        validarTelefono(cliente.getTelefono());
        validarCorreo(cliente.getCorreo());
    }

    private void validarTelefono(String telefono) {
        if (clienteRepository.existsByTelefono(telefono)) {
            throw new IllegalArgumentException("El teléfono ya está registrado");
        }
    }

    private void validarCorreo(String correo) {
        if (clienteRepository.existsByCorreo(correo)) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
    }
}