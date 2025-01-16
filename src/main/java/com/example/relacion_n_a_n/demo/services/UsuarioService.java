package com.example.relacion_n_a_n.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.models.UsuarioModel;

public interface UsuarioService {

    UsuarioModel createUsuario(UsuarioModel usuario);

    List<UsuarioModel> allUsuarios();

    Optional<UsuarioModel> buscarPorID(Long usuario_id);

    UsuarioModel registrarEstudiosPorUsuario(Long id_usuario, List<EstudioModel> nuevosEstudios);

    List<EstudioModel> ConsultarEstudiosPorUsuario(Long id_usuario);
}
