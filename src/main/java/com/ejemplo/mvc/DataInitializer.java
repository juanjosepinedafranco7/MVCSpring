package com.ejemplo.mvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ejemplo.mvc.model.Aprendiz;
import com.ejemplo.mvc.model.Ficha;
import com.ejemplo.mvc.repository.AprendizRepository;
import com.ejemplo.mvc.repository.FichaRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner cargarDatos(FichaRepository fichaRepository,
                                         AprendizRepository aprendizRepository) {
        return args -> {

            Ficha f1 = fichaRepository.save(new Ficha("2758643", "Análisis y Desarrollo de Software", true));
            Ficha f2 = fichaRepository.save(new Ficha("2891234", "Diseño Gráfico", true));
            Ficha f3 = fichaRepository.save(new Ficha("2654321", "Gestión Empresarial", false));

            System.out.println("✔ Fichas cargadas: " + fichaRepository.count());

            aprendizRepository.save(new Aprendiz("Carlos Andrés Pérez", "carlos@example.com", "1001234567", f1.getNumero()));
            aprendizRepository.save(new Aprendiz("Laura Milena García", "laura@example.com", "1009876543", f1.getNumero()));
            aprendizRepository.save(new Aprendiz("Sebastián Torres", "sebastian@example.com", "1005551234", f2.getNumero()));
            aprendizRepository.save(new Aprendiz("María Fernanda López", "maria@example.com", "1003339876", f2.getNumero()));
            aprendizRepository.save(new Aprendiz("Juan David Ramírez", "juan@example.com", "1007771234", f1.getNumero()));

            System.out.println("✔ Aprendices cargados: " + aprendizRepository.count());
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("  Sistema Académico MVC - Spring Boot listo");
            System.out.println("  URL: http://localhost:8080");
            System.out.println("  Consola H2: http://localhost:8080/h2-console");
            System.out.println("══════════════════════════════════════════════════");
        };
    }
}
