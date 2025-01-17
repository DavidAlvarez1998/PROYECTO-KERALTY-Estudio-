package com.example.relacion_n_a_n.demo.services.ServicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.relacion_n_a_n.demo.DTOs.request.EstudioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;
import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.repositories.EstudioRepository;
import com.example.relacion_n_a_n.demo.services.EstudioService;

@Service
public class EstudioServiceImpl implements EstudioService {

    @Autowired
    EstudioRepository estudioRepository;

    // Conversión de EstudioRequestDTO a EstudioModel
    public EstudioModel convertEstudioRequestDTOToEstudioModel(EstudioRequestDTO estudioRequestDTO) {
        return EstudioModel.builder()
                .nombre(estudioRequestDTO.getNombre())
                .horas(estudioRequestDTO.getHoras())
                .fechaInicio(estudioRequestDTO.getFechaInicio())
                .fechaFin(estudioRequestDTO.getFechaFin())
                .build();
    }

    // Conversión de EstudioModel a EstudioResponseDTO
    public EstudioResponseDTO convertEstudioModelToEstudioResponseDTO(EstudioModel estudioModel) {
        return EstudioResponseDTO.builder()
                .estudioId(estudioModel.getEstudio_id())
                .nombre(estudioModel.getNombre())
                .horas(estudioModel.getHoras())
                .fechaInicio(estudioModel.getFechaInicio())
                .fechaFin(estudioModel.getFechaFin())
                .build();
    }

    // Crear un nuevo estudio
    public EstudioResponseDTO createEstudio(EstudioRequestDTO estudio) {
        EstudioModel estudioModel = convertEstudioRequestDTOToEstudioModel(estudio);
        EstudioModel savedEstudio = estudioRepository.save(estudioModel);
        return convertEstudioModelToEstudioResponseDTO(savedEstudio);
    }

    // Obtener todos los estudios
    public List<EstudioResponseDTO> allEstudios() {
        List<EstudioModel> estudios = estudioRepository.findAll();
        return estudios.stream()
                .map(this::convertEstudioModelToEstudioResponseDTO)
                .collect(Collectors.toList());
    }

    // Obtener un estudio por ID
    public EstudioResponseDTO findById(Long estudio_id) {
        Optional<EstudioModel> estudio = estudioRepository.findById(estudio_id);
        if (estudio.isPresent()) {
            return convertEstudioModelToEstudioResponseDTO(estudio.get());
        } else {
            System.out.println("Estudio no encontrado");
            return null;
        }
    }

    // Obtener un estudio por name
    public EstudioResponseDTO findByNombre(String nombre) {
        Optional<EstudioModel> estudio = estudioRepository.findByNombre(nombre);
        if (estudio.isPresent()) {
            return convertEstudioModelToEstudioResponseDTO(estudio.get());
        } else {
            return null;
        }
    }
}
