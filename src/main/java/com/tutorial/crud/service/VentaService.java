package com.tutorial.crud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.tutorial.crud.entity.Venta;
import com.tutorial.crud.repository.VentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VentaService {
    
    @Autowired
    VentaRepository ventaRepository;

    public List<Venta> list() {
        return ventaRepository.findAll();
    }

    public List<Venta> getActivos() {
        return ventaRepository.findByEstado(true).get();
    }

    public Optional<Venta> getOne(int id) {
        return ventaRepository.findById(id);
    }

    public Optional<Venta> getByNumeroFactura(String numeroFactura) {
        return ventaRepository.findByNumeroFactura(numeroFactura);
    }

    public boolean existsByNumeroFactura(String numeroFactura) {
        return ventaRepository.existsByNumeroFactura(numeroFactura);
    }

    public void save(Venta venta) {
        ventaRepository.save(venta);
    }

    public Boolean delete(int id) {
        return this.changeStatus(id, false);
    }

    public Boolean changeStatus(int id, boolean status) {
        Venta venta = ventaRepository.getOne(id);
        venta.setEstado(status);
        ventaRepository.save(venta);
        return venta.getEstado();
    }

    public void update(Venta venta) {
        ventaRepository.save(venta);
    }

    public boolean existsById(int id) {
        return ventaRepository.existsById(id);
    }

}
