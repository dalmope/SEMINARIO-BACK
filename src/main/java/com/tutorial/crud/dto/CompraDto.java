package com.tutorial.crud.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.tutorial.crud.entity.Producto;

public class CompraDto {
    @NotBlank
    private String numeroFactura;
    private int cantidad;
    private String proveedor;
    private int usuario;
    private Set<Producto> productos;
    private Boolean estado;


     public CompraDto() {
    }

    public CompraDto(@NotBlank String numeroFactura, int cantidad, String proveedor, int usuario, Set<Producto> productos, Boolean estado) {
        this.numeroFactura = numeroFactura;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.productos = productos;
        this.estado = estado;
    }

     public CompraDto(@NotBlank String numeroFactura, int cantidad) {
        this.numeroFactura = numeroFactura;
        this.cantidad = cantidad;
     }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Set<Producto> getProducto() {
        return productos;
    }

    public void setProducto(Set<Producto> productos) {
        this.productos = productos;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
