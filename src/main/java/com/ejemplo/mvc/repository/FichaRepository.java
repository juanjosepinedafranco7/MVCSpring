package com.ejemplo.mvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.mvc.model.Ficha;


@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

    Optional<Ficha> findByNumero(String numero);

    boolean existsByNumero(String numero);

    List<Ficha> findByActivaTrue();
}
