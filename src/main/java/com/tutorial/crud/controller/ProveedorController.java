package com.tutorial.crud.controller;

import java.util.List;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.ProveedorDto;
import com.tutorial.crud.entity.Proveedor;
import com.tutorial.crud.service.ProveedorService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {
    
     @Autowired
    ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> list(){
        List<Proveedor> list = proveedorService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> get(@PathVariable("id") int id){
        Proveedor proveedor = proveedorService.getOne(id).get();
        return new ResponseEntity<>(proveedor, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProveedorDto proveedorDto){
        if(StringUtils.isBlank(proveedorDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(proveedorService.existsByNombre(proveedorDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Proveedor proveedor = new Proveedor (proveedorDto.getNombre());
        proveedorService.save(proveedor);
        return new ResponseEntity<>(new Mensaje("Proveedor creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProveedorDto proveedorDto){
        if(!proveedorService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(proveedorService.existsByNombre(proveedorDto.getNombre()) && proveedorService.getByNombre(proveedorDto.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(proveedorDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Proveedor proveedor = proveedorService.getOne(id).get();
        proveedor.setNombre(proveedorDto.getNombre());
        proveedorService.save(proveedor);
        return new ResponseEntity<>(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!proveedorService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        proveedorService.delete(id);
        return new ResponseEntity<>(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
}
