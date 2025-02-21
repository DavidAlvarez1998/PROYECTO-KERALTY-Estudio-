package com.example.relacion_n_a_n.demo.services;

import java.util.List;

import com.example.relacion_n_a_n.demo.DTOs.request.EstudioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.request.UsuarioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.UsuarioResponseDTO;

public interface UsuarioService {

    UsuarioResponseDTO createUsuario(UsuarioRequestDTO usuario);

    List<UsuarioResponseDTO> allUsuarios();

    UsuarioResponseDTO buscarPorID(Long id_usuario);

    UsuarioResponseDTO registrarEstudiosPorUsuario(Long id_usuario, List<EstudioRequestDTO> nuevosEstudios);

    List<EstudioResponseDTO> consultarEstudiosPorUsuario(Long id_usuario);

    void deleteUsuario(Long id_usuario);

    UsuarioResponseDTO updateUsuario(Long id_usuario, UsuarioRequestDTO usuarioRequest);
}
