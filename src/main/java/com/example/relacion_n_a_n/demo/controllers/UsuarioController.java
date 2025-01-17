package com.example.relacion_n_a_n.demo.controllers;

import com.example.relacion_n_a_n.demo.DTOs.request.EstudioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.request.UsuarioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.UsuarioResponseDTO;
import com.example.relacion_n_a_n.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping()
    public UsuarioResponseDTO createUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.createUsuario(usuarioRequestDTO);
    }

    @GetMapping()
    public List<UsuarioResponseDTO> allUsuarios() {
        return usuarioService.allUsuarios();
    }

    @GetMapping(path = "/{id}")
    public UsuarioResponseDTO obtenerUsuarioPorId(@PathVariable("id") Long usuario_id) {
        return usuarioService.buscarPorID(usuario_id);
    }

    // Consultar estudios de un usuario
    @GetMapping(path = "/estudios/{id}")
    public List<EstudioResponseDTO> ConsultarEstudiosPorUsuario(@PathVariable("id") Long usuario_id) {
        return usuarioService.ConsultarEstudiosPorUsuario(usuario_id);
    }

    // Asignarle estudios a un usuario
    @PostMapping("/{id}/estudios")
    public UsuarioResponseDTO addEstudiosToUsuario(@PathVariable Long id,
            @RequestBody List<EstudioRequestDTO> estudios) {
        return usuarioService.registrarEstudiosPorUsuario(id, estudios);
    }
}
