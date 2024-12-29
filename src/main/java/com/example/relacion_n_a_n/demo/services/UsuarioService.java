package com.example.relacion_n_a_n.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.models.UsuarioModel;
import com.example.relacion_n_a_n.demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EstudioService estudioService;

    public UsuarioModel createUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> allUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel registrarEstudiosPorUsuario(Long id_usuario, List<EstudioModel> nuevosEstudios) {

        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id_usuario);

        if (usuarioOptional.isPresent()) {
            UsuarioModel usuario = usuarioOptional.get();

            List<EstudioModel> estudiosActuales = usuario.getEstudioList();

            if (estudiosActuales == null) {
                estudiosActuales = new ArrayList<>();
            }

            for (EstudioModel nuevoEstudio : nuevosEstudios) {
                Optional<EstudioModel> estudioDB = estudioService.findByName(nuevoEstudio.getNombre());

                if (estudioDB.isPresent()) {
                    EstudioModel estudioExistente = estudioDB.get();
                    if (!estudiosActuales.contains(estudioExistente)) {
                        estudiosActuales.add(estudioExistente);
                    }
                } else {
                    estudiosActuales.add(nuevoEstudio);
                }
            }

            usuario.setEstudioList(estudiosActuales);

            usuario = createUsuario(usuario);

            return usuario;
        } else {
            return null;
        }
    }

    public Optional<UsuarioModel> ConsultarEstudiosPorUsuario(Long id_usuario) {
        return usuarioRepository.findById(id_usuario);
    }

}