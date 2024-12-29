package com.example.relacion_n_a_n.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.relacion_n_a_n.demo.models.UsuarioModel;
import com.example.relacion_n_a_n.demo.services.UsuarioService;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/usuario")
    public UsuarioModel createUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @GetMapping("/usuario")
    public List<UsuarioModel> allUsuarios() {
        return usuarioService.allUsuarios();
    }

}
