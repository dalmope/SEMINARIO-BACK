package com.tutorial.crud.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numeroFactura;
    private int cantidad;
    private String proveedor;
    private int usuario;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "compra_productos", joinColumns = @JoinColumn(name = "compra_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productos = new HashSet<>();
    private Boolean estado;

    public Compra() {

    }

    public Compra(String numeroFactura, int cantidad, String proveedor, int usuario, Set<Producto> productos,
            Boolean estado) {
        this.numeroFactura = numeroFactura;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.productos = productos;
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

    public Set<Producto>  getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
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
                + productos + ", proveedor=" + proveedor + ", usuario=" + usuario + "]";
    }

}
