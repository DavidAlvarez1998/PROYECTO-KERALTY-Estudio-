package com.example.relacion_n_a_n.demo.services;

import java.util.List;

import com.example.relacion_n_a_n.demo.DTOs.request.EstudioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;

public interface EstudioService {

    EstudioResponseDTO createEstudio(EstudioRequestDTO estudio);

    List<EstudioResponseDTO> allEstudios();

    EstudioResponseDTO findById(Long estudio_id);

    EstudioResponseDTO findByNombre(String nombre);

    EstudioResponseDTO updateEstudio(Long estudioId, EstudioRequestDTO estudioRequest);

    void deleteEstudio(Long estudioId);

}
