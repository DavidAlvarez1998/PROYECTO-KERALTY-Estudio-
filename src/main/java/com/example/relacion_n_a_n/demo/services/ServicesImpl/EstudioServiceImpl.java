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
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;
import com.example.relacion_n_a_n.demo.Exceptions.EntityCreationException;
import com.example.relacion_n_a_n.demo.Exceptions.EntityDeleteException;
import com.example.relacion_n_a_n.demo.Exceptions.EntityUpdateException;
import com.example.relacion_n_a_n.demo.Exceptions.ResourceNotFoundException;
import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.repositories.EstudioRepository;
import com.example.relacion_n_a_n.demo.services.EstudioService;

@Service
public class EstudioServiceImpl implements EstudioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstudioServiceImpl.class);

    @Autowired
    EstudioRepository estudioRepository;

    // --------------------------------------------------
    // Conversiones Model <-> DTO
    // --------------------------------------------------
    public EstudioModel convertEstudioRequestDTOToEstudioModel(EstudioRequestDTO estudioRequestDTO) {
        return EstudioModel.builder()
                .nombre(estudioRequestDTO.getNombre())
                .horas(estudioRequestDTO.getHoras())
                .fechaInicio(estudioRequestDTO.getFechaInicio())
                .fechaFin(estudioRequestDTO.getFechaFin())
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
    public EstudioResponseDTO createEstudio(EstudioRequestDTO estudio) {
        try {
            EstudioModel estudioModel = convertEstudioRequestDTOToEstudioModel(estudio);
            EstudioModel savedEstudio = estudioRepository.save(estudioModel);
            return convertEstudioModelToEstudioResponseDTO(savedEstudio);
        } catch (Exception e) {
            LOGGER.error("Error al crear estudio: {}", e.getMessage());
            throw new EntityCreationException("No se pudo crear el estudio. " + e.getMessage(), e);
        }
    }

    // --------------------------------------------------
    // READ
    // --------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public List<EstudioResponseDTO> allEstudios() {
        List<EstudioModel> estudios = estudioRepository.findAll();
        return estudios.stream()
                .map(this::convertEstudioModelToEstudioResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EstudioResponseDTO findById(Long estudio_id) {
        // orElseThrow -> si no existe, ResourceNotFoundException
        EstudioModel estudio = estudioRepository.findById(estudio_id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudio no encontrado, ID: " + estudio_id));
        return convertEstudioModelToEstudioResponseDTO(estudio);
    }

    @Override
    @Transactional(readOnly = true)
    public EstudioResponseDTO findByNombre(String nombre) {
        Optional<EstudioModel> estudio = estudioRepository.findByNombre(nombre);
        // Si no existe, devuelves null o lanzas ResourceNotFoundException
        // Te muestro ambas opciones:

        // Opción A: Lanzar ResourceNotFoundException
        return estudio
                .map(this::convertEstudioModelToEstudioResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Estudio no encontrado con nombre: " + nombre));

        // Opción B: Retornar null si no existe
        // if (estudio.isPresent()) {
        // return convertEstudioModelToEstudioResponseDTO(estudio.get());
        // } else {
        // return null;
        // }
    }

    // --------------------------------------------------
    // UPDATE
    // --------------------------------------------------
    @Override
    @Transactional
    public EstudioResponseDTO updateEstudio(Long estudioId, EstudioRequestDTO estudioRequest) {
        try {
            // 1. Verificar si existe
            EstudioModel estudioModel = estudioRepository.findById(estudioId)
                    .orElseThrow(() -> new ResourceNotFoundException("Estudio no encontrado, ID: " + estudioId));

            // 2. Actualizar campos
            estudioModel.setNombre(estudioRequest.getNombre());
            estudioModel.setHoras(estudioRequest.getHoras());
            estudioModel.setFechaInicio(estudioRequest.getFechaInicio());
            estudioModel.setFechaFin(estudioRequest.getFechaFin());

            // 3. Guardar
            EstudioModel updatedEstudio = estudioRepository.save(estudioModel);
            return convertEstudioModelToEstudioResponseDTO(updatedEstudio);

        } catch (ResourceNotFoundException e) {
            // Re-lanzamos si no existe
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error al actualizar estudio: {}", e.getMessage());
            throw new EntityUpdateException("No se pudo actualizar el estudio. " + e.getMessage(), e);
        }
    }

    // --------------------------------------------------
    // DELETE
    // --------------------------------------------------
    @Override
    @Transactional
    public void deleteEstudio(Long estudioId) {
        try {
            // 1. Verificar si existe
            EstudioModel estudioModel = estudioRepository.findById(estudioId)
                    .orElseThrow(() -> new ResourceNotFoundException("Estudio no encontrado, ID: " + estudioId));

            // 2. Eliminar
            estudioRepository.delete(estudioModel);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error al eliminar estudio: {}", e.getMessage());
            throw new EntityDeleteException("No se pudo eliminar el estudio. " + e.getMessage(), e);
        }
    }
}
