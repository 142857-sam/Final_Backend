package com.cso.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "Materiales")
public class Materiales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "cantidad", nullable = false, length = 50)
    private int cantidad;

    @Column(name = "prestado", nullable = false)
    private Boolean prestado;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private MentorArte mentor;

    


    public Materiales(int id, String nombre, String estado, int cantidad, Boolean prestado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.cantidad = cantidad;
        this.prestado = prestado;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean isPrestado() {
        return this.prestado;
    }

    public Boolean getPrestado() {
        return this.prestado;
    }

    public void setPrestado(Boolean prestado) {
        this.prestado = prestado;
    }

    public MentorArte getMentor() {
        return this.mentor;
    }
 
    public void setMentor(MentorArte mentor) {
        this.mentor = mentor;
    }

}
