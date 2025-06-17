// ClaveGeneratorService.java
package utez.edu.mx.u3_04_msgr.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class ClaveGeneratorService {
    private final Random random = new Random();

    public String generarClaveCede(Long id) {
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String numerosAleatorios = String.format("%04d", random.nextInt(10000));
        return String.format("C%d-%s-%s", id, fecha, numerosAleatorios);
    }

    public String generarClaveAlmacen(String claveCede, Long id) {
        return String.format("%s-A%d", claveCede, id);
    }
}
