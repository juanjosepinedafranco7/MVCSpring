package com.ejemplo.mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "fichas")
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de ficha es obligatorio")
    @Column(name = "numero", nullable = false, unique = true, length = 20)
    private String numero;

    @NotBlank(message = "El programa es obligatorio")
    @Column(name = "programa", nullable = false, length = 150)
    private String programa;

    @NotNull(message = "El estado es obligatorio")
    @Column(name = "activa", nullable = false)
    private boolean activa = true;


    public Ficha() {}

    public Ficha(String numero, String programa, boolean activa) {
        this.numero = numero;
        this.programa = programa;
        this.activa = activa;
    }

  

    public boolean estaActiva() {
        return this.activa;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }

    @Override
    public String toString() {
        return "Ficha{numero='" + numero + "', programa='" + programa + "', activa=" + activa + "}";
    }
}
