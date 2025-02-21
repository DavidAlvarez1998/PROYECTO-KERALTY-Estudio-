package com.example.relacion_n_a_n.demo.services.ServicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.relacion_n_a_n.demo.DTOs.request.EstudioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.request.UsuarioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.UsuarioResponseDTO;
import com.example.relacion_n_a_n.demo.Exceptions.EntityCreationException;
import com.example.relacion_n_a_n.demo.Exceptions.EntityDeleteException;
import com.example.relacion_n_a_n.demo.Exceptions.EntityUpdateException;
import com.example.relacion_n_a_n.demo.Exceptions.ResourceNotFoundException;
import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.models.RelacionId;
import com.example.relacion_n_a_n.demo.models.RelacionModel;
import com.example.relacion_n_a_n.demo.models.UsuarioModel;
import com.example.relacion_n_a_n.demo.repositories.UsuarioRepository;
import com.example.relacion_n_a_n.demo.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RelacionServiceImpl relacionService;

    @Autowired
    EstudioServiceImpl estudioService;

    // --------------------------------------------------
    // Métodos de conversión (Model <-> DTO)
    // --------------------------------------------------

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

    // --------------------------------------------------
    // CREATE
    // --------------------------------------------------
    @Override
    @Transactional
    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO usuario) {
        try {
            UsuarioModel usuarioModel = convertUsuarioRequestDTOToUsuarioModel(usuario);
            UsuarioModel usuarioDB = usuarioRepository.save(usuarioModel);
            return convertUsuarioModelToUsuarioResponseDTO(usuarioDB);
        } catch (Exception e) {
            LOGGER.error("Error al crear usuario: {}", e.getMessage());
            throw new EntityCreationException("No se pudo crear el usuario. " + e.getMessage(), e);
        }
    }

    // --------------------------------------------------
    // READ (listar y buscar por ID)
    // --------------------------------------------------
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
        // orElseThrow -> si no encuentra, lanza ResourceNotFoundException
        UsuarioModel usuario = usuarioRepository.findById(usuario_id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, ID: " + usuario_id));

        LOGGER.info("Usuario encontrado para ID: {}", usuario_id);
        return convertUsuarioModelToUsuarioResponseDTO(usuario);
    }

    // --------------------------------------------------
    // UPDATE
    // --------------------------------------------------
    @Override
    @Transactional
    public UsuarioResponseDTO updateUsuario(Long usuarioId, UsuarioRequestDTO usuarioRequest) {
        try {
            // 1. Buscar si existe
            UsuarioModel user = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, ID: " + usuarioId));

            // 2. Actualizar campos
            user.setNombres(usuarioRequest.getNombres());
            user.setApellidos(usuarioRequest.getApellidos());
            user.setEmail(usuarioRequest.getEmail());
            user.setCelular(usuarioRequest.getCelular());
            user.setEstado(usuarioRequest.getEstado());

            // 3. Guardar
            UsuarioModel updatedUser = usuarioRepository.save(user);

            return convertUsuarioModelToUsuarioResponseDTO(updatedUser);

        } catch (ResourceNotFoundException e) {
            // Re-lanzamos la de "no encontrado"
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error al actualizar usuario: {}", e.getMessage());
            throw new EntityUpdateException("No se pudo actualizar el usuario. " + e.getMessage(), e);
        }
    }

    // --------------------------------------------------
    // DELETE
    // --------------------------------------------------
    @Override
    @Transactional
    public void deleteUsuario(Long id_usuario) {
        try {
            // 1. Verificar si existe
            UsuarioModel user = usuarioRepository.findById(id_usuario)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id_usuario));

            // 2. Eliminar
            usuarioRepository.delete(user);

        } catch (ResourceNotFoundException e) {
            throw e; // 404
        } catch (Exception e) {
            LOGGER.error("Error al eliminar usuario: {}", e.getMessage());
            throw new EntityDeleteException("No se pudo eliminar el usuario. " + e.getMessage(), e);
        }
    }

    // --------------------------------------------------
    // Lógica para asociar estudios a un usuario
    // --------------------------------------------------
    @Override
    @Transactional
    public UsuarioResponseDTO registrarEstudiosPorUsuario(Long id_usuario, List<EstudioRequestDTO> nuevosEstudios) {
        // 1. Buscar usuario, si no existe -> ResourceNotFoundException
        UsuarioResponseDTO usuario = buscarPorID(id_usuario);

        // 2. Lógica de asignación
        List<RelacionModel> listaRelacionModels = relacionService.findByUsuarioId(usuario.getUsuarioId());

        for (EstudioRequestDTO nuevoEstudio : nuevosEstudios) {
            boolean estudioYaExistente = listaRelacionModels.stream()
                    .anyMatch(relacion -> relacion.getEstudio().getNombre().equals(nuevoEstudio.getNombre()));

            if (!estudioYaExistente) {
                EstudioResponseDTO estudioDB = estudioService.findByNombre(nuevoEstudio.getNombre());

                if (estudioDB != null) {
                    RelacionId relacionId = new RelacionId(usuario.getUsuarioId(), estudioDB.getEstudioId());
                    RelacionModel relacion = RelacionModel.builder()
                            .relacionId(relacionId)
                            .usuario(convertUsuarioResponseDTOToUsuarioModel(usuario))
                            .estudio(convertEstudioResponseDTOToEstudioModel(estudioDB))
                            .estado("activo")
                            .build();
                    relacionService.createRelacion(relacion);

                } else {
                    EstudioResponseDTO estudioGuardado = estudioService.createEstudio(nuevoEstudio);
                    RelacionId relacionId = new RelacionId(usuario.getUsuarioId(), estudioGuardado.getEstudioId());

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
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstudioResponseDTO> consultarEstudiosPorUsuario(Long id_usuario) {
        List<RelacionModel> relaciones = relacionService.findByUsuarioId(id_usuario);

        List<EstudioModel> estudios = relaciones.stream()
                .map(RelacionModel::getEstudio)
                .collect(Collectors.toList());

        return estudios.stream()
                .map(estudio -> estudioService.convertEstudioModelToEstudioResponseDTO(estudio))
                .collect(Collectors.toList());
    }
}
