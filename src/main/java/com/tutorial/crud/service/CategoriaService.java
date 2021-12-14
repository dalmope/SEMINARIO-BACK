package com.tutorial.crud.service;

import com.tutorial.crud.entity.Categoria;
import com.tutorial.crud.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

      public List<Categoria> list(){
        return categoriaRepository.findAll();
    }

    public List<Categoria> getActivos(){
        return categoriaRepository.findByEstado(true).get();
    }

    public Optional<Categoria> getOne(int id){
        return categoriaRepository.findById(id);
    }

    public Optional<Categoria> getByNombre(String nombre){
        return categoriaRepository.findByNombre(nombre);
    }

    public void  save(Categoria categoria){
        categoriaRepository.save(categoria);
    }

    public Boolean delete(int id){
        return this.changeStatus(id, false);
    }

    public Boolean changeStatus(int id, boolean status){
        Categoria categoria = categoriaRepository.getOne(id);
        categoria.setEstado(status);
        categoriaRepository.save(categoria);
        return categoria.getEstado();
    }

    public boolean existsById(int id){
        return categoriaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return categoriaRepository.existsByNombre(nombre);
    }
}
