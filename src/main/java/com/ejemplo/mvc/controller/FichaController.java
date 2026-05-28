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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.mvc.model.Ficha;
import com.ejemplo.mvc.service.FichaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @GetMapping
    public ResponseEntity<List<Ficha>> listar() {
        return ResponseEntity.ok(fichaService.listarTodas());
    }

    
    @GetMapping("/activas")
    public ResponseEntity<List<Ficha>> listarActivas() {
        return ResponseEntity.ok(fichaService.listarActivas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return fichaService.buscarPorId(id)
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Ficha no encontrada con ID: " + id)));
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Ficha ficha) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(fichaService.guardar(ficha));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            fichaService.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "Ficha eliminada correctamente."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> estadisticas() {
        return ResponseEntity.ok(Map.of("total", fichaService.contarTotal()));
    }
}
