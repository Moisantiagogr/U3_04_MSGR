package utez.edu.mx.u3_04_msgr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.u3_04_msgr.model.Cede;
import utez.edu.mx.u3_04_msgr.service.CedeService;

import java.util.List;

@RestController
@RequestMapping("/api/cedes")
@RequiredArgsConstructor
public class CedeController {
    private final CedeService cedeService;

    public CedeController(CedeService cedeService) {
        this.cedeService = cedeService;
    }

    @PostMapping
    public ResponseEntity<Cede> crear(@RequestBody Cede cede) {
        return ResponseEntity.ok(cedeService.crear(cede));
    }

    @GetMapping
    public ResponseEntity<List<Cede>> obtenerTodas() {
        return ResponseEntity.ok(cedeService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cede> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cedeService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cede> actualizar(@PathVariable Long id, @RequestBody Cede cede) {
        return ResponseEntity.ok(cedeService.actualizar(id, cede));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cedeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}