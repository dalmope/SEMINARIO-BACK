package com.tutorial.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Compra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numeroFactura;
    private int cantidad;
    private String proveedor;
    private int usuario;
    private int producto;
    private Boolean estado;
  
    public Compra(){

    }

    public Compra(String numeroFactura, int cantidad, String proveedor, int usuario, int producto, Boolean estado){
        this.numeroFactura = numeroFactura;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.producto = producto;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Compra [cantidad=" + cantidad + ", id=" + id + ", numeroFactura=" + numeroFactura + ", producto="
                + producto + ", proveedor=" + proveedor + ", usuario=" + usuario + "]";
    }

    
}
