package com.tutorial.crud.repository;

import java.util.List;
import java.util.Optional;

import com.tutorial.crud.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByNombre(String nombre);
    Optional <Cliente> findByNombre(String nombre);
      Optional<List<Cliente>> findByEstado(boolean b);
}
