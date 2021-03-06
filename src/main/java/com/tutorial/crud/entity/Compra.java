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
    private String proveedor;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "compra_productos", joinColumns = @JoinColumn(name = "compra_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private Set<Producto> productos = new HashSet<>();
    private Boolean estado;
    private Double total;

    public Compra() {
    }

    public Compra(String numeroFactura, String proveedor, @NotNull Set<Producto> productos, Boolean estado, Double total) { 
        this.numeroFactura = numeroFactura;
        this.proveedor = proveedor;
        this.productos = productos;
        this.estado = estado;
        this.total = total;
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


    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Compra [estado=" + estado + ", id=" + id + ", numeroFactura=" + numeroFactura
                + ", productos=" + productos + ", proveedor=" + proveedor + "]";
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
        Compra other = (Compra) obj;
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
