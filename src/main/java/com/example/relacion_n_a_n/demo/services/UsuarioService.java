package com.example.relacion_n_a_n.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.models.RelacionModel;
import com.example.relacion_n_a_n.demo.models.UsuarioModel;
import com.example.relacion_n_a_n.demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RelacionService relacionService;

    @Autowired
    EstudioService estudioService;

    public UsuarioModel createUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> allUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> buscarPorID(Long usuario_id) {
        return usuarioRepository.findById(usuario_id);
    }

    public UsuarioModel registrarEstudiosPorUsuario(Long id_usuario, List<EstudioModel> nuevosEstudios) {
        Optional<UsuarioModel> usuarioOptional = buscarPorID(id_usuario);

        if (usuarioOptional.isPresent()) {
            UsuarioModel usuario = usuarioOptional.get();

            List<RelacionModel> listaRelacionModels = relacionService.findByUsuarioId(usuario.getUsuario_id());

            for (EstudioModel nuevoEstudio : nuevosEstudios) {

                boolean estudioYaExistente = listaRelacionModels.stream()
                        .anyMatch(relacion -> relacion.getEstudio().getNombre().equals(nuevoEstudio.getNombre()));

                if (!estudioYaExistente) {

                    Optional<EstudioModel> estudioDB = estudioService.findByName(nuevoEstudio.getNombre());

                    if (estudioDB.isPresent()) {
                        EstudioModel estudioExistente = estudioDB.get();
                        RelacionModel relacion = new RelacionModel();
                        relacion.setUsuario(usuario);
                        relacion.setEstudio(estudioExistente);
                        relacion.setEstado("activo");

                        relacionService.createRelacion(relacion);
                    } else {
                        EstudioModel estudioGuardado = estudioService.createEstudio(nuevoEstudio);

                        RelacionModel relacion = new RelacionModel();
                        relacion.setUsuario(usuario);
                        relacion.setEstudio(estudioGuardado);
                        relacion.setEstado("activo");

                        relacionService.createRelacion(relacion);
                    }
                }

            }

            return usuario;
        } else {
            return null;
        }
    }

    public List<RelacionModel> ConsultarEstudiosPorUsuario(Long id_usuario) {
        return relacionService.findByUsuarioId(id_usuario);
    }
}
