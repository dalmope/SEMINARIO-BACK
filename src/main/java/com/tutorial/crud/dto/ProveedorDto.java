package com.tutorial.crud.dto;

import javax.validation.constraints.NotBlank;

public class ProveedorDto {
    @NotBlank
    private String nombre;

    private Boolean estado;

    public ProveedorDto(){

    }

    public ProveedorDto(String nombre, Boolean estado){
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
        return "ProveedorDto [nombre=" + nombre + "]";
    }

    

}
