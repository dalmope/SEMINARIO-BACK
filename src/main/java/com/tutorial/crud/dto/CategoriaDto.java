package com.tutorial.crud.dto;

import javax.validation.constraints.NotBlank;

public class CategoriaDto {
    @NotBlank
    private String nombre;
    private Boolean estado;

    public CategoriaDto(){

    }

    public CategoriaDto (@NotBlank String nombre, Boolean estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

      public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ProductoDto{" +
                "nombre='" + nombre + '}';
    }
}
