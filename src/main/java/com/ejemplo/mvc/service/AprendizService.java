package com.ejemplo.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.mvc.model.Aprendiz;
import com.ejemplo.mvc.repository.AprendizRepository;

@Service
public class AprendizService {

    @Autowired
    private AprendizRepository aprendizRepository;

    
    public List<Aprendiz> listarTodos() {
        return aprendizRepository.findAll();
    }

    
    public Optional<Aprendiz> buscarPorId(Long id) {
        return aprendizRepository.findById(id);
    }

   
    public Optional<Aprendiz> buscarPorDocumento(String documento) {
        return aprendizRepository.findByDocumento(documento);
    }

   
    public List<Aprendiz> listarActivos() {
        return aprendizRepository.findByActivoTrue();
    }

    
    public List<Aprendiz> listarPorFicha(String ficha) {
        return aprendizRepository.findByFicha(ficha);
    }

    public List<Aprendiz> buscarPorNombre(String nombre) {
        return aprendizRepository.buscarPorNombre(nombre);
    }

   
    public Aprendiz guardar(Aprendiz aprendiz) {
        if (aprendizRepository.existsByCorreo(aprendiz.getCorreo())) {
            throw new IllegalArgumentException(
                "Ya existe un aprendiz registrado con el correo: " + aprendiz.getCorreo()
            );
        }
        if (aprendizRepository.existsByDocumento(aprendiz.getDocumento())) {
            throw new IllegalArgumentException(
                "Ya existe un aprendiz registrado con el documento: " + aprendiz.getDocumento()
            );
        }
        aprendiz.setActivo(true);
        return aprendizRepository.save(aprendiz);
    }

   
    public Aprendiz actualizar(Long id, Aprendiz datosNuevos) {
        Aprendiz existente = aprendizRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Aprendiz no encontrado con ID: " + id));

        existente.setNombre(datosNuevos.getNombre());
        existente.setCorreo(datosNuevos.getCorreo());
        existente.setFicha(datosNuevos.getFicha());
        existente.setActivo(datosNuevos.isActivo());

        return aprendizRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!aprendizRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró el aprendiz con ID: " + id);
        }
        aprendizRepository.deleteById(id);
    }

    public long contarTotal() {
        return aprendizRepository.count();
    }

    public long contarActivos() {
        return aprendizRepository.findByActivoTrue().size();
    }
}
