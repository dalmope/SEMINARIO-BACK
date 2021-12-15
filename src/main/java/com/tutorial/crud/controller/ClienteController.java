package com.tutorial.crud.controller;

import java.util.List;

import com.tutorial.crud.dto.ClienteDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Cliente;
import com.tutorial.crud.service.ClienteService;

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
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;

    @ApiOperation("Devuelve una lista de Clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> list(){
        List<Cliente> list = clienteService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Devuelve una lista de Clientes activos")
    @GetMapping("/activos")
    public ResponseEntity<List<Cliente>> listActivos(){
        List<Cliente> list = clienteService.getActivos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    @ApiOperation("Activa un Cliente por su ID")
    @PutMapping("/activar/{id}")
    public ResponseEntity<Mensaje> activar(@PathVariable("id") int id){
        clienteService.changeStatus(id, true);
        return new ResponseEntity<>(new Mensaje("Proveedor activado"), HttpStatus.OK);
    }

      @ApiOperation("Devuelve un cliente por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable("id") int id){
        Cliente cliente = clienteService.getOne(id).get();
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @ApiOperation("Crea un proveedor")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto){
        if(StringUtils.isBlank(clienteDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(clienteService.existsByNombre(clienteDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Cliente cliente = new Cliente (clienteDto.getNombre(), clienteDto.getCedula(), clienteDto.getNumeroTelefono(), clienteDto.getEmail(), clienteDto.getEstado());

        clienteService.save(cliente);
        return new ResponseEntity<>(new Mensaje("Cliente creado"), HttpStatus.OK);
    }

    @ApiOperation("Actualiza un Cliente")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ClienteDto clienteDto){
        if(!clienteService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(clienteService.existsByNombre(clienteDto.getNombre()) && clienteService.getByNombre(clienteDto.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(clienteDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Cliente cliente = clienteService.getOne(id).get();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setEstado(clienteDto.getEstado());
        clienteService.save(cliente);
        return new ResponseEntity<>(new Mensaje("cliente actualizado"), HttpStatus.OK);
    }
    
     @ApiOperation("Elimina un Cliente, cambiando su estado a false")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!clienteService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Boolean estado = clienteService.delete(id);
        return new ResponseEntity<>(new Mensaje("Cliente eliminado, estado: " + estado ), HttpStatus.OK);
    }
}
