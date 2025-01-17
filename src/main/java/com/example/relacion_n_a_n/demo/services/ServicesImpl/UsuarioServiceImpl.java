package com.example.relacion_n_a_n.demo.services.ServicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.relacion_n_a_n.demo.DTOs.request.EstudioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.request.UsuarioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.UsuarioResponseDTO;
import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.models.RelacionId;
import com.example.relacion_n_a_n.demo.models.RelacionModel;
import com.example.relacion_n_a_n.demo.models.UsuarioModel;
import com.example.relacion_n_a_n.demo.repositories.UsuarioRepository;
import com.example.relacion_n_a_n.demo.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RelacionServiceImpl relacionService;

    @Autowired
    EstudioServiceImpl estudioService;

    // Métodos de conversión
    public UsuarioResponseDTO convertUsuarioModelToUsuarioResponseDTO(UsuarioModel usuarioModel) {
        return UsuarioResponseDTO.builder()
                .usuarioId(usuarioModel.getUsuario_id())
                .nombres(usuarioModel.getNombres())
                .apellidos(usuarioModel.getApellidos())
                .email(usuarioModel.getEmail())
                .celular(usuarioModel.getCelular())
                .estado(usuarioModel.getEstado())
                .build();
    }

    public EstudioModel convertEstudioResponseDTOToEstudioModel(EstudioResponseDTO estudioResponseDTO) {
        return EstudioModel.builder()
                .estudio_id(estudioResponseDTO.getEstudioId())
                .nombre(estudioResponseDTO.getNombre())
                .horas(estudioResponseDTO.getHoras())
                .fechaInicio(estudioResponseDTO.getFechaInicio())
                .fechaFin(estudioResponseDTO.getFechaFin())
                .build();
    }

    public EstudioResponseDTO convertEstudioModelToEstudioResponseDTO(EstudioModel estudioModel) {
        return EstudioResponseDTO.builder()
                .estudioId(estudioModel.getEstudio_id())
                .nombre(estudioModel.getNombre())
                .horas(estudioModel.getHoras())
                .fechaInicio(estudioModel.getFechaInicio())
                .fechaFin(estudioModel.getFechaFin())
                .build();
    }

    public UsuarioModel convertUsuarioRequestDTOToUsuarioModel(UsuarioRequestDTO usuarioRequestDTO) {
        return UsuarioModel.builder()
                .nombres(usuarioRequestDTO.getNombres())
                .apellidos(usuarioRequestDTO.getApellidos())
                .email(usuarioRequestDTO.getEmail())
                .celular(usuarioRequestDTO.getCelular())
                .estado(usuarioRequestDTO.getEstado())
                .build();
    }

    public UsuarioModel convertUsuarioResponseDTOToUsuarioModel(UsuarioResponseDTO usuarioResponseDTO) {
        return UsuarioModel.builder()
                .usuario_id(usuarioResponseDTO.getUsuarioId())
                .nombres(usuarioResponseDTO.getNombres())
                .apellidos(usuarioResponseDTO.getApellidos())
                .email(usuarioResponseDTO.getEmail())
                .celular(usuarioResponseDTO.getCelular())
                .estado(usuarioResponseDTO.getEstado())
                .build();
    }

    @Override
    @Transactional
    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO usuario) {
        UsuarioModel usuarioModel = convertUsuarioRequestDTOToUsuarioModel(usuario);
        UsuarioModel usuarioDB = usuarioRepository.save(usuarioModel); // try
        return convertUsuarioModelToUsuarioResponseDTO(usuarioDB);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> allUsuarios() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertUsuarioModelToUsuarioResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorID(Long usuario_id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(usuario_id);
        if (usuario.isPresent()) {
            return convertUsuarioModelToUsuarioResponseDTO(usuario.get());
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public UsuarioResponseDTO registrarEstudiosPorUsuario(Long id_usuario, List<EstudioRequestDTO> nuevosEstudios) {
        UsuarioResponseDTO usuario = buscarPorID(id_usuario);

        if (usuario != null) {
            List<RelacionModel> listaRelacionModels = relacionService.findByUsuarioId(usuario.getUsuarioId());

            for (EstudioRequestDTO nuevoEstudio : nuevosEstudios) {

                boolean estudioYaExistente = listaRelacionModels.stream()
                        .anyMatch(relacion -> relacion.getEstudio().getNombre().equals(nuevoEstudio.getNombre()));

                if (!estudioYaExistente) {

                    EstudioResponseDTO estudioDB = estudioService.findByNombre(nuevoEstudio.getNombre());

                    if (estudioDB != null) {

                        RelacionId relacionId = new RelacionId(usuario.getUsuarioId(),
                                estudioDB.getEstudioId());

                        RelacionModel relacion = RelacionModel.builder()
                                .relacionId(relacionId)
                                .usuario(convertUsuarioResponseDTOToUsuarioModel(usuario))
                                .estudio(convertEstudioResponseDTOToEstudioModel(estudioDB))
                                .estado("activo")
                                .build();

                        relacionService.createRelacion(relacion);

                    } else {

                        EstudioResponseDTO estudioGuardado = estudioService.createEstudio(nuevoEstudio);

                        RelacionId relacionId = new RelacionId(usuario.getUsuarioId(),
                                estudioGuardado.getEstudioId());

                        RelacionModel relacion = RelacionModel.builder()
                                .relacionId(relacionId)
                                .usuario(convertUsuarioResponseDTOToUsuarioModel(usuario))
                                .estudio(convertEstudioResponseDTOToEstudioModel(estudioGuardado))
                                .estado("activo")
                                .build();

                        relacionService.createRelacion(relacion);
                    }
                }

            }

            return usuario;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstudioResponseDTO> ConsultarEstudiosPorUsuario(Long id_usuario) {
        List<RelacionModel> relaciones = relacionService.findByUsuarioId(id_usuario);

        List<EstudioModel> estudios = relaciones.stream()
                .map(RelacionModel::getEstudio)
                .collect(Collectors.toList());

        return estudios.stream()
                .map(estudio -> estudioService.convertEstudioModelToEstudioResponseDTO(estudio))
                .collect(Collectors.toList());
    }

}
