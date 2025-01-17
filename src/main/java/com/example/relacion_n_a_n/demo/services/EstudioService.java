package com.example.relacion_n_a_n.demo.services;

import java.util.List;

import com.example.relacion_n_a_n.demo.DTOs.request.EstudioRequestDTO;
import com.example.relacion_n_a_n.demo.DTOs.response.EstudioResponseDTO;

public interface EstudioService {

    EstudioResponseDTO createEstudio(EstudioRequestDTO estudio);

    List<EstudioResponseDTO> allEstudios();

    EstudioResponseDTO findByNombre(String nombre);

    EstudioResponseDTO findById(Long id);
}
