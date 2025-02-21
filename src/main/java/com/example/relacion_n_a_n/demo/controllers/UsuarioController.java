package com.example.relacion_n_a_n.demo.controllers;

import com.example.relacion_n_a_n.demo.DTOs.request.EstudioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.request.UsuarioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.UsuarioResponseDTO;
import com.example.relacion_n_a_n.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:5173", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // CREATE
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioCreado = usuarioService.createUsuario(usuarioRequestDTO);
        // 201 CREATED + objeto en el body
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    // READ (listar)
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> allUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.allUsuarios();
        // 200 OK con la lista
        return ResponseEntity.ok(usuarios);
    }

    // READ (buscar por ID)
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable("id") Long usuario_id) {
        UsuarioResponseDTO usuario = usuarioService.buscarPorID(usuario_id);
        // 200 OK con el usuario
        return ResponseEntity.ok(usuario);
    }

    // Consultar estudios de un usuario
    @GetMapping("/estudios/{id}")
    public ResponseEntity<List<EstudioResponseDTO>> ConsultarEstudiosPorUsuario(@PathVariable("id") Long usuario_id) {
        List<EstudioResponseDTO> estudios = usuarioService.consultarEstudiosPorUsuario(usuario_id);
        // 200 OK con la lista de estudios
        return ResponseEntity.ok(estudios);
    }

    // Asignarle estudios a un usuario
    @PostMapping("/{id}/estudios")
    public ResponseEntity<UsuarioResponseDTO> addEstudiosToUsuario(
            @PathVariable Long id,
            @RequestBody List<EstudioRequestDTO> estudios) {
        UsuarioResponseDTO usuarioActualizado = usuarioService.registrarEstudiosPorUsuario(id, estudios);
        // 200 OK con el usuario (ya que no es un recurso nuevo, es un update)
        return ResponseEntity.ok(usuarioActualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.deleteUsuario(id);
        // 204 No Content
        return ResponseEntity.noContent().build();
    }

    // Editar Usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioRequestDTO usuarioRequest) {
        UsuarioResponseDTO usuarioActualizado = usuarioService.updateUsuario(id, usuarioRequest);
        return ResponseEntity.ok(usuarioActualizado);
    }
}
