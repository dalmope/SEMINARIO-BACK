package com.tutorial.crud.controller;

import java.util.List;

import com.tutorial.crud.dto.CompraDto;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Compra;
import com.tutorial.crud.service.CompraService;
import com.tutorial.crud.service.ProductoService;

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
@RequestMapping("/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    @Autowired
    CompraService compraService;

    @Autowired
    ProductoService productoService;

    @ApiOperation("Muestra una lista de Compras")
    @GetMapping
    public ResponseEntity<List<Compra>> list() {
        List<Compra> list = compraService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Devuelve una compra por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!compraService.existsById(id)) {
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Compra compra = compraService.getOne(id).get();
        return new ResponseEntity<>(compra, HttpStatus.OK);
    }

    @ApiOperation("Devuelve una compra  por el numero de factura")
    @GetMapping("/numeroFactura/{numeroFactura}")
    public ResponseEntity<?> getByNombre(@PathVariable("numeroFactura") String numeroFactura) {
        if (!compraService.existsByNombre(numeroFactura))
            return new ResponseEntity<>(new Mensaje("no existe esta compra"), HttpStatus.NOT_FOUND);
        Compra compra = compraService.getByNombre(numeroFactura).get();
        return new ResponseEntity<>(compra, HttpStatus.OK);
    }

    @ApiOperation("Guarda una compra")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CompraDto compraDto) {
        Double total = 0.0;
        if (StringUtils.isBlank(compraDto.getNumeroFactura()))
            return new ResponseEntity<>(new Mensaje("el numeor de factura es obligatorio"), HttpStatus.BAD_REQUEST);
        if (compraService.existsByNombre(compraDto.getNumeroFactura()))
            return new ResponseEntity<>(new Mensaje("ese numero de factura ya existe"), HttpStatus.BAD_REQUEST);

        for (Producto s : compraDto.getProductos()) {
            Producto producto = productoService.getOne(s.getId()).get();

            if (!productoService.existsById(s.getId()))
                return new ResponseEntity<>(new Mensaje("el producto con id " + s.getId() + " no existe"), HttpStatus.BAD_REQUEST);
            if (s.getCantidad() <= 0)
                return new ResponseEntity<>(new Mensaje("la cantidad debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

            total += producto.getPrecio() * s.getCantidad();
            producto.setCantidad(producto.getCantidad() + s.getCantidad());
            productoService.changeStatus(producto.getId(), true);
            productoService.save(producto);
        }

        Compra compra = new Compra(compraDto.getNumeroFactura(), compraDto.getProveedor(), compraDto.getProductos(), true, total);

        compraService.save(compra);
        return new ResponseEntity<>(new Mensaje("Compra creada"), HttpStatus.OK);
    }

    @ApiOperation("Actualiza una compra")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody CompraDto compraDto) {
        if (!compraService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (compraService.existsByNombre(compraDto.getNumeroFactura())
                && compraService.getByNombre(compraDto.getNumeroFactura()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("ese numero de factura  ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(compraDto.getNumeroFactura()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        for (Producto s : compraDto.getProductos()) {

            Producto producto = productoService.getOne(s.getId()).get();

            if (producto.getEstado() && s.getEstado()) {
                return new ResponseEntity<>(
                        new Mensaje("Compra invalida: verifique que el producto exista o que su estado sea: true"),
                        HttpStatus.BAD_REQUEST);
            }

            // if (producto.getCantidad() < s.getCantidad()) {
            // return new ResponseEntity<>(new Mensaje("Compra invalida: no hay suficiente
            // cantidad de productos"), HttpStatus.BAD_REQUEST);
            // }

            producto.setCantidad(producto.getCantidad() + s.getCantidad());

            // if (producto.getCantidad() == 0) {
            // producto.setEstado(false);
            // }
            

            productoService.save(producto);
        }

        Compra compra = compraService.getOne(id).get();
        compraService.save(compra);
        return new ResponseEntity<>(new Mensaje("Compra actualizado"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un compra")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!compraService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        compraService.delete(id);
        return new ResponseEntity<>(new Mensaje("Compra eliminada"), HttpStatus.OK);
    }

}
