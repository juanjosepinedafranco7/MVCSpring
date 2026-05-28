package com.ejemplo.mvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.mvc.model.Aprendiz;
import com.ejemplo.mvc.service.AprendizService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/aprendices")
public class AprendizController {

    @Autowired
    private AprendizService aprendizService;



    @GetMapping
    public ResponseEntity<List<Aprendiz>> listar() {
        return ResponseEntity.ok(aprendizService.listarTodos());
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return aprendizService.buscarPorId(id)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Aprendiz no encontrado con ID: " + id)));
    }

    

    @GetMapping("/buscar")
    public ResponseEntity<List<Aprendiz>> buscarPorNombre(
            @RequestParam String nombre) {
        return ResponseEntity.ok(aprendizService.buscarPorNombre(nombre));
    }

   

    @GetMapping("/activos")
    public ResponseEntity<List<Aprendiz>> listarActivos() {
        return ResponseEntity.ok(aprendizService.listarActivos());
    }

 

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Aprendiz aprendiz) {
        try {
            Aprendiz guardado = aprendizService.guardar(aprendiz);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Aprendiz aprendiz) {
        try {
            Aprendiz actualizado = aprendizService.actualizar(id, aprendiz);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
        }
    }

 

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            aprendizService.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "Aprendiz eliminado correctamente."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
        }
    }

 


    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> estadisticas() {
        return ResponseEntity.ok(Map.of(
            "total",   aprendizService.contarTotal(),
            "activos", aprendizService.contarActivos()
        ));
    }
}
