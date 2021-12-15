package com.tutorial.crud.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numeroFactura;
    private String cliente;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "venta_productos", joinColumns = @JoinColumn(name = "venta_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productos = new HashSet<>();
    private Boolean estado;
    private Double total;

    public Venta() {
    }

    public Venta(String numeroFactura, String cliente,  Set<Producto> productos, Double total) {
        this.numeroFactura = numeroFactura;
        this.productos = productos;
        this.cliente = cliente;
        this.estado = true;
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Venta [cliente=" + cliente + ", id=" + id + ", numeroFactura=" + numeroFactura + ", productos="
                + productos + ", total=" + total + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((numeroFactura == null) ? 0 : numeroFactura.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Venta other = (Venta) obj;
        if (id != other.id)
            return false;
        if (numeroFactura == null) {
            if (other.numeroFactura != null)
                return false;
        } else if (!numeroFactura.equals(other.numeroFactura))
            return false;
        return true;
    }

    

}
