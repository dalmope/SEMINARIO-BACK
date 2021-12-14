package com.tutorial.crud.repository;

import java.util.List;
import java.util.Optional;

import com.tutorial.crud.entity.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    java.util.Optional<Categoria> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    Optional<List<Categoria>> findByEstado(boolean b);
}