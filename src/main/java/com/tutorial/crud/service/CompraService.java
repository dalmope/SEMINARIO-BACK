package com.tutorial.crud.service;



import java.util.List;
import java.util.Optional;

import com.tutorial.crud.entity.Compra;
import com.tutorial.crud.repository.CompraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompraService {
    
    @Autowired
    CompraRepository compraRepository;

     public List<Compra> list(){
        return compraRepository.findAll();
    }

    public Optional<Compra> getOne(int id){
        return compraRepository.findById(id);
    }

    public Optional<Compra> getByNombre(String numeroFactura){
        return compraRepository.findByNombre(numeroFactura);
    }

    public void  save(Compra compra){
        compraRepository.save(compra);
    }

    public void delete(int id){
        compraRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return compraRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return compraRepository.existsByNombre(nombre);
    }
}
