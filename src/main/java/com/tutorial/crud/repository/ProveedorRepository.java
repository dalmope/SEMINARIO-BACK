package com.tutorial.crud.repository;

import java.util.Optional;

import com.tutorial.crud.entity.Proveedor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
     Optional<Proveedor> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
