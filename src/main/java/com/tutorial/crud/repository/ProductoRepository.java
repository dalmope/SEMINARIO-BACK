package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    List<Producto> findByCategoria(int categoria);
}
