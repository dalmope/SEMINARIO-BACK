package com.tutorial.crud.dto;

import javax.validation.constraints.NotBlank;

public class CompraDto {
    @NotBlank
    private String numeroFactura;
    private int cantidad;
    private String proveedor;
    private int usuario;
    private int producto;
    private Boolean estado;


     public CompraDto() {
    }

    public CompraDto(@NotBlank String numeroFactura, int cantidad, String proveedor, int usuario, int producto, Boolean estado) {
        this.numeroFactura = numeroFactura;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.producto = producto;
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

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

     

}
