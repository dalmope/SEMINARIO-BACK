package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{
    java.util.Optional<Compra> findByNombre(String numeroFactura);
    boolean existsByNombre(String numeroFactura);
}
