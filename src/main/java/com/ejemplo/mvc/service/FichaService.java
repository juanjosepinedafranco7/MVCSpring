package com.ejemplo.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.mvc.model.Ficha;
import com.ejemplo.mvc.repository.FichaRepository;


@Service
public class FichaService {

    @Autowired
    private FichaRepository fichaRepository;

    public List<Ficha> listarTodas() {
        return fichaRepository.findAll();
    }

  
    public List<Ficha> listarActivas() {
        return fichaRepository.findByActivaTrue();
    }

    public Optional<Ficha> buscarPorId(Long id) {
        return fichaRepository.findById(id);
    }

    public Ficha guardar(Ficha ficha) {
        if (fichaRepository.existsByNumero(ficha.getNumero())) {
            throw new IllegalArgumentException(
                "Ya existe una ficha con el número: " + ficha.getNumero()
            );
        }
        return fichaRepository.save(ficha);
    }

    public void eliminar(Long id) {
        if (!fichaRepository.existsById(id)) {
            throw new IllegalArgumentException("Ficha no encontrada con ID: " + id);
        }
        fichaRepository.deleteById(id);
    }

    public long contarTotal() {
        return fichaRepository.count();
    }
}
