package com.tutorial.crud.service;

import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> list() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getOne(int id) {
        return productoRepository.findById(id);
    }

    public Optional<Producto> getByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    public List<Producto> getActivos() {
        return productoRepository.findByEstado(true).get();
    }

    public Boolean delete(int id) {
        return this.changeStatus(id, false);
    }

    public boolean existsById(int id) {
        return productoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return productoRepository.existsByNombre(nombre);
    }

    public List<Producto> findById_categoria(int id_categoria) {
        return productoRepository.findByCategoria(id_categoria);
    }

    public Boolean changeStatus(int id, boolean status) {
        Producto producto = productoRepository.getOne(id);
        producto.setEstado(status);
        productoRepository.save(producto);
        return producto.getEstado();
    }
}
