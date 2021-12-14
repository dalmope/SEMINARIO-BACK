package com.tutorial.crud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.tutorial.crud.entity.Proveedor;
import com.tutorial.crud.repository.ProveedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProveedorService {
    
    @Autowired
    ProveedorRepository proveedorRepository;

      public List<Proveedor> list(){
        return proveedorRepository.findAll();
    }

    public List<Proveedor> getActivos(){
        return proveedorRepository.findByEstado(true).get();
    }

    public Optional<Proveedor> getOne(int id){
        return proveedorRepository.findById(id);
    }

    public Optional<Proveedor> getByNombre(String nombre){
        return proveedorRepository.findByNombre(nombre);
    }

    public void  save(Proveedor categoria){
        proveedorRepository.save(categoria);
    }

    public Boolean delete(int id){
       return this.changeStatus(id, false);
    }

    public Boolean changeStatus(int id, boolean status){
        Proveedor proveedor = proveedorRepository.getOne(id);
        proveedor.setEstado(status);
        proveedorRepository.save(proveedor);
        return proveedor.getEstado();
    }

    public void update(Proveedor proveedor){
        proveedorRepository.save(proveedor);
    }

    public boolean existsById(int id){
        return proveedorRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return proveedorRepository.existsByNombre(nombre);
    }
}
