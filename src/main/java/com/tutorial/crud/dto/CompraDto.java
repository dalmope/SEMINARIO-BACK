package com.tutorial.crud.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.tutorial.crud.entity.Producto;

public class CompraDto {
    @NotBlank
    private String numeroFactura;
    private String proveedor;
    private Set<Producto> productos;

    public CompraDto() {
    }

    public CompraDto(@NotBlank String numeroFactura, String proveedor, Set<Producto> productos) {
        this.numeroFactura = numeroFactura;
        this.proveedor = proveedor;
        this.productos = productos;
    }

     public CompraDto(@NotBlank String numeroFactura, int cantidad) {
        this.numeroFactura = numeroFactura;
     }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

}
