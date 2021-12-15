package com.tutorial.crud.controller;

import java.util.List;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.VentaDto;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.entity.Venta;
import com.tutorial.crud.service.ProductoService;
import com.tutorial.crud.service.VentaService;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")

public class VentaController {

    @Autowired
    VentaService ventaService;

    @Autowired
    ProductoService productoService;

    @ApiOperation("Muestra una lista de Compras")
    @GetMapping
    public ResponseEntity<List<Venta>> list() {
        List<Venta> list = ventaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Devuelve una compra por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!ventaService.existsById(id)) {
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Venta venta = ventaService.getOne(id).get();
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    @ApiOperation("Devuelve una compra  por el numero de factura")
    @GetMapping("/numeroFactura/{numeroFactura}")
    public ResponseEntity<?> getByNombre(@PathVariable("numeroFactura") String numeroFactura) {
        if (!ventaService.existsByNumeroFactura(numeroFactura))
            return new ResponseEntity<>(new Mensaje("no existe esta compra"), HttpStatus.NOT_FOUND);
        Venta venta = ventaService.getByNumeroFactura(numeroFactura).get();
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    @ApiOperation("Guarda una compra")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody VentaDto ventaDto) {
        Double total = 0.0;
        if (StringUtils.isBlank(ventaDto.getNumeroFactura()))
            return new ResponseEntity<>(new Mensaje("el numero de factura es obligatorio"), HttpStatus.BAD_REQUEST);
        if (ventaService.existsByNumeroFactura(ventaDto.getNumeroFactura()))
            return new ResponseEntity<>(new Mensaje("ese numero de factura ya existe"), HttpStatus.BAD_REQUEST);

        for (Producto s : ventaDto.getProductos()) {
            Producto producto = productoService.getOne(s.getId()).get();

            if (!productoService.existsById(s.getId()))
                return new ResponseEntity<>(new Mensaje("el producto con id " + s.getId() + " no existe"),
                        HttpStatus.BAD_REQUEST);
            if (s.getCantidad() <= 0)
                return new ResponseEntity<>(new Mensaje("la cantidad debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
            if (producto.getCantidad() < s.getCantidad()) {
                return new ResponseEntity<>(new Mensaje("Compra invalida: no hay suficiente cantidad de productos"),
                        HttpStatus.BAD_REQUEST);
            }

            total += producto.getPrecio() * s.getCantidad();
            producto.setCantidad(producto.getCantidad() - s.getCantidad());

            if (producto.getCantidad() == 0) {
                producto.setEstado(false);
            }

            productoService.save(producto);
        }

        Venta venta = new Venta(ventaDto.getNumeroFactura(), ventaDto.getCliente(), ventaDto.getProductos(), total);

        ventaService.save(venta);
        return new ResponseEntity<>(new Mensaje("Compra creada"), HttpStatus.OK);
    }

    @ApiOperation("Elimina un compra")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!ventaService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        ventaService.delete(id);
        return new ResponseEntity<>(new Mensaje("Compra eliminada"), HttpStatus.OK);
    }

}
