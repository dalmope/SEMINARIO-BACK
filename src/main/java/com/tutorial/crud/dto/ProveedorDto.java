package com.tutorial.crud.dto;

import javax.validation.constraints.NotBlank;

public class ProveedorDto {
    @NotBlank
    private String nombre;

    public ProveedorDto(){

    }

    public ProveedorDto(String nombre){
        this.nombre = nombre;
    }

      public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ProveedorDto [nombre=" + nombre + "]";
    }

    

}
