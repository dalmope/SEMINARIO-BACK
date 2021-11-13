package com.tutorial.crud.security.controller;

import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController{

     @Autowired
    UsuarioService usuarioService;

    @ApiOperation("Muestra una lista de usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario> list = usuarioService.listarUsuarios();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}