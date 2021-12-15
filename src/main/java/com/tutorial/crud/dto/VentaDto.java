package com.tutorial.crud.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.tutorial.crud.entity.Producto;

public class VentaDto {
    @NotBlank
    private String numeroFactura;
    private String cliente;
    private Set<Producto> productos;

    VentaDto() {
    }

    public VentaDto(@NotBlank String numeroFactura, String cliente, Set<Producto> productos) {
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.productos = productos;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

}
