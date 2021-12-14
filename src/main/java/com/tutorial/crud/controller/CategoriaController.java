package com.tutorial.crud.controller;

import java.util.List;

import com.tutorial.crud.dto.CategoriaDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Categoria;
import com.tutorial.crud.service.CategoriaService;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
    
    @Autowired
    CategoriaService categoriaService;

    @ApiOperation("Devuelve una lista de categorias")
    @GetMapping
    public ResponseEntity<List<Categoria>> list(){
        List<Categoria> list = categoriaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Devuelve una categoria por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> get(@PathVariable("id") int id){
        Categoria categoria = categoriaService.getOne(id).get();
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @ApiOperation("Devuelve una lista de categorias activas")
    @GetMapping("/activos")
    public ResponseEntity<List<Categoria>> listActivos(){
        List<Categoria> list = categoriaService.getActivos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Activa una categoria por su ID")
    @PutMapping("/activar/{id}")
    public ResponseEntity<Mensaje> activar(@PathVariable("id") int id){
        categoriaService.changeStatus(id, true);
        return new ResponseEntity<>(new Mensaje("categoria activada"), HttpStatus.OK);
    }

    @ApiOperation("Crea una categoria")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoriaDto categoriaDto){
        if(StringUtils.isBlank(categoriaDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(categoriaService.existsByNombre(categoriaDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Categoria categoria = new Categoria(categoriaDto.getNombre(), categoriaDto.getEstado());
        categoriaService.save(categoria);
        return new ResponseEntity<>(new Mensaje("categoria creada"), HttpStatus.OK);
    }

    @ApiOperation("Actualiza una categoria")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody CategoriaDto categoriaDto){
        if(!categoriaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(categoriaService.existsByNombre(categoriaDto.getNombre()) && categoriaService.getByNombre(categoriaDto.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(categoriaDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Categoria categoria = categoriaService.getOne(id).get();
        categoria.setNombre(categoriaDto.getNombre());
        categoria.setEstado(categoriaDto.getEstado());
        categoriaService.save(categoria);
        return new ResponseEntity<>(new Mensaje("Categoria actualizada"), HttpStatus.OK);
    }

    @ApiOperation("Elimina una categoria, cambiando el estado a false")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!categoriaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Boolean estado = categoriaService.delete(id);
        return new ResponseEntity<>(new Mensaje("Categoria eliminada, estado:" + estado), HttpStatus.OK);
    }

}
