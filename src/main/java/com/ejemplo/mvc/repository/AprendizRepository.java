package com.ejemplo.mvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ejemplo.mvc.model.Aprendiz;


@Repository
public interface AprendizRepository extends JpaRepository<Aprendiz, Long> {

    Optional<Aprendiz> findByDocumento(String documento);

   
    boolean existsByCorreo(String correo);


    boolean existsByDocumento(String documento);

   
    List<Aprendiz> findByFicha(String ficha);

    
    List<Aprendiz> findByActivoTrue();

   
    @Query("SELECT a FROM Aprendiz a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Aprendiz> buscarPorNombre(@Param("nombre") String nombre);
}
