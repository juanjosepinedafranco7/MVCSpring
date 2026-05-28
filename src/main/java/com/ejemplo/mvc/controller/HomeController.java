package com.ejemplo.mvc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.mvc.service.AprendizService;
import com.ejemplo.mvc.service.FichaService;


@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private AprendizService aprendizService;

    @Autowired
    private FichaService fichaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> resumen() {
        return ResponseEntity.ok(Map.of(
            "sistema",         "Sistema Académico MVC - Spring Boot",
            "totalAprendices", aprendizService.contarTotal(),
            "totalActivos",    aprendizService.contarActivos(),
            "totalFichas",     fichaService.contarTotal(),
            "endpoints",       Map.of(
                "aprendices", "/api/aprendices",
                "fichas",     "/api/fichas",
                "h2-console", "/h2-console"
            )
        ));
    }
}
