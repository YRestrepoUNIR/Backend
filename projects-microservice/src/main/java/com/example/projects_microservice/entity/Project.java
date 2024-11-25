package com.example.projects_microservice.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "proyectos")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(nullable = false)
    private String state;

    // Constructor vacío
    public Project() {}

    // Constructor con parámetros
    public Project(String nombre, String descripcion, Date fechaInicio, Date fechaFin, String estado) {
        this.name = nombre;
        this.description = descripcion;
        this.startDate = fechaInicio;
        this.endDate = fechaFin;
        this.state = estado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String descripcion) {
        this.description = descripcion;
    }

    public Date getFechaInicio() {
        return startDate;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.startDate = fechaInicio;
    }

    public Date getFechaFin() {
        return endDate;
    }

    public void setFechaFin(Date fechaFin) {
        this.endDate = fechaFin;
    }

    public String getEstado() {
        return state;
    }

    public void setEstado(String estado) {
        this.state = estado;
    }
}
