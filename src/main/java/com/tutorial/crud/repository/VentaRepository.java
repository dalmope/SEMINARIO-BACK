package com.tutorial.crud.repository;

import java.util.List;
import java.util.Optional;

import com.tutorial.crud.entity.Venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    Optional<Venta> findByNumeroFactura(String numeroFactura);
    boolean existsByNumeroFactura(String numeroFactura);

    Optional<List<Venta>> findByCliente(String cliente);
    boolean existsByCliente(String cliente);

    Optional<List<Venta>> findByEstado(boolean estado);
    boolean existsByEstado(boolean estado);


}
    

