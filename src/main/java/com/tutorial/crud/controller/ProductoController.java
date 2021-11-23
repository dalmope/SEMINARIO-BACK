package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.ProductoDto;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.service.ProductoService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @ApiOperation("Muestra una lista de productos")
    @GetMapping
    public ResponseEntity<List<Producto>> list(){
        List<Producto> list = productoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Devuelve un producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!productoService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @ApiOperation("Devuelve un producto por nombre")
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> getByNombre(@PathVariable("nombre") String nombre){
        if(!productoService.existsByNombre(nombre))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @ApiOperation("Guarda un producto")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()==null || productoDto.getPrecio()<0 )
            return new ResponseEntity<>(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(productoService.existsByNombre(productoDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio(), productoDto.getCantidad());
        producto.setIva(productoDto.getIva());
        producto.setRetencion(productoDto.getRetencion());
        producto.setCantidad_minima(productoDto.getCantidad_minima());
        producto.setCategoria(productoDto.getCategoria());
        
        productoService.save(producto);
        return new ResponseEntity<>(new Mensaje("producto creado"), HttpStatus.OK);
    }

    @ApiOperation("Actualiza un producto")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductoDto productoDto){
        if(!productoService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(productoService.existsByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()==null || productoDto.getPrecio()<0 )
            return new ResponseEntity<>(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Producto producto = productoService.getOne(id).get();
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        producto.setCantidad(productoDto.getCantidad());
        producto.setIva(productoDto.getIva());
        producto.setRetencion(productoDto.getRetencion());
        producto.setCantidad_minima(productoDto.getCantidad_minima());
        producto.setCategoria(productoDto.getCategoria());
        productoService.save(producto);

        return new ResponseEntity<>(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un producto")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productoService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity<>(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

    @ApiOperation("Muestra una lista de productos por categoria")
    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> findById_categoria(@PathVariable("id")int id){
        List<Producto> list = productoService.findById_categoria(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
