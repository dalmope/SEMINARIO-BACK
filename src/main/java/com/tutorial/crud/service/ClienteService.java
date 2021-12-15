package com.tutorial.crud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.tutorial.crud.entity.Cliente;
import com.tutorial.crud.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> list(){
        return clienteRepository.findAll();
    }

    public List<Cliente> getActivos(){
        return clienteRepository.findByEstado(true).get();
    }
     public Optional<Cliente> getOne(int id){
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> getByNombre(String nombre){
        return clienteRepository.findByNombre(nombre);
    }

    public void  save(Cliente cliente){
        clienteRepository.save(cliente);
    }

    public Boolean delete(int id){
       return this.changeStatus(id, false);
    }

    public Boolean changeStatus(int id, boolean status){
        Cliente cliente = clienteRepository.getOne(id);
        cliente.setEstado(status);
        clienteRepository.save(cliente);
        return cliente.getEstado();
    }

    public void update(Cliente cliente){
        clienteRepository.save(cliente);
    }

    public boolean existsById(int id){
        return clienteRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return clienteRepository.existsByNombre(nombre);
    }
}
