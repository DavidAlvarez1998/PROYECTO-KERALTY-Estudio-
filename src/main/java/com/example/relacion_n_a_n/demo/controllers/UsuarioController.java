package com.example.relacion_n_a_n.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.models.UsuarioModel;
import com.example.relacion_n_a_n.demo.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping()
    public UsuarioModel createUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @GetMapping()
    public List<UsuarioModel> allUsuarios() {
        return usuarioService.allUsuarios();
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long usuario_id) {
        return usuarioService.buscarPorID(usuario_id);
    }

    // Consultar estudios de un usuario
    @GetMapping(path = "/estudios/{id}")
    public List<EstudioModel> ConsultarEstudiosPorUsuario(@PathVariable("id") Long usuario_id) {
        return usuarioService.ConsultarEstudiosPorUsuario(usuario_id);
    }

    // Asignarle estudios a un usuario
    @PostMapping("/{id}/estudios")
    public UsuarioModel addEstudiosToUsuario(@PathVariable Long id, @RequestBody List<EstudioModel> estudios) {
        return usuarioService.registrarEstudiosPorUsuario(id, estudios);
    }

}
