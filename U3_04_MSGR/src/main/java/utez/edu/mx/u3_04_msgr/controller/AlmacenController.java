package utez.edu.mx.u3_04_msgr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.u3_04_msgr.model.Almacen;
import utez.edu.mx.u3_04_msgr.model.Cliente;
import utez.edu.mx.u3_04_msgr.model.EstadoAlmacen;
import utez.edu.mx.u3_04_msgr.service.AlmacenService;

import java.util.List;


@RestController
@RequestMapping("/api/almacenes")
@RequiredArgsConstructor
public class AlmacenController {
    public AlmacenController(AlmacenService almacenService) {
        this.almacenService = almacenService;
    }

    private final AlmacenService almacenService;

    @PostMapping
    public ResponseEntity<Almacen> crear(@RequestBody Almacen almacen) {
        return ResponseEntity.ok(almacenService.crear(almacen));
    }

    @GetMapping
    public ResponseEntity<List<Almacen>> obtenerTodos() {
        return ResponseEntity.ok(almacenService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Almacen> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(almacenService.getbyId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Almacen> actualizar(@PathVariable Long id, @RequestBody Almacen almacen) {
        return ResponseEntity.ok(almacenService.actualizar(id, almacen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        almacenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/vender")
    public ResponseEntity<Almacen> vender(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(almacenService.cambiarEstado(id, EstadoAlmacen.VENDIDO, cliente));
    }

    @PutMapping("/{id}/rentar")
    public ResponseEntity<Almacen> rentar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(almacenService.cambiarEstado(id, EstadoAlmacen.RENTADO, cliente));
    }
}