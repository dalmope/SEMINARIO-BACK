package com.tutorial.crud.entity;

import javax.persistence.*;

@Entity
public class Cliente {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    private int cedula;

    private String numeroTelefono;

    private String email;

    private Boolean estado;

    public Cliente(){

    }

    public Cliente(String nombre, int cedula, String numeroTelefono, String email, Boolean estado) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }  

}
