package com.ejemplo.mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "aprendices")
public class Aprendiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Ingrese un correo electrónico válido")
    @Column(name = "correo", nullable = false, unique = true, length = 150)
    private String correo;

    @NotBlank(message = "El documento es obligatorio")
    @Size(min = 5, max = 20, message = "El documento debe tener entre 5 y 20 caracteres")
    @Column(name = "documento", nullable = false, unique = true, length = 20)
    private String documento;

    @NotBlank(message = "La ficha es obligatoria")
    @Column(name = "ficha", nullable = false, length = 20)
    private String ficha;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

   

    public Aprendiz() {
    }

    public Aprendiz(String nombre, String correo, String documento, String ficha) {
        this.nombre = nombre;
        this.correo = correo;
        this.documento = documento;
        this.ficha = ficha;
        this.activo = true;
    }

    

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getFicha() { return ficha; }
    public void setFicha(String ficha) { this.ficha = ficha; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }




    public boolean estaActivo() {
        return this.activo;
    }

    @Override
    public String toString() {
        return "Aprendiz{id=" + id + ", nombre='" + nombre + "', ficha='" + ficha + "', activo=" + activo + "}";
    }
}
