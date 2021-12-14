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

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {
    
     @Autowired
    ProveedorService proveedorService;

    @ApiOperation("Devuelve una lista de Proveedores")
    @GetMapping
    public ResponseEntity<List<Proveedor>> list(){
        List<Proveedor> list = proveedorService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Devuelve una lista de Proveedores activos")
    @GetMapping("/activos")
    public ResponseEntity<List<Proveedor>> listActivos(){
        List<Proveedor> list = proveedorService.getActivos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Activa un Proveedor por su ID")
    @PutMapping("/activar/{id}")
    public ResponseEntity<Mensaje> activar(@PathVariable("id") int id){
        proveedorService.changeStatus(id, true);
        return new ResponseEntity<>(new Mensaje("Proveedor activado"), HttpStatus.OK);
    }

    @ApiOperation("Devuelve un proveedor por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> get(@PathVariable("id") int id){
        Proveedor proveedor = proveedorService.getOne(id).get();
        return new ResponseEntity<>(proveedor, HttpStatus.OK);
    }
    
    @ApiOperation("Crea un proveedor")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProveedorDto proveedorDto){
        if(StringUtils.isBlank(proveedorDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(proveedorService.existsByNombre(proveedorDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Proveedor proveedor = new Proveedor (proveedorDto.getNombre(), proveedorDto.getEstado());
        proveedorService.save(proveedor);
        return new ResponseEntity<>(new Mensaje("Proveedor creado"), HttpStatus.OK);
    }

    @ApiOperation("Actualiza un proveedor")
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
        proveedor.setEstado(proveedorDto.getEstado());
        proveedorService.save(proveedor);
        return new ResponseEntity<>(new Mensaje("Proveedor actualizado"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un proveedor, cambiando su estado a false")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!proveedorService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Boolean estado = proveedorService.delete(id);
        return new ResponseEntity<>(new Mensaje("Proveedor eliminado, estado: " + estado ), HttpStatus.OK);
    }
}
