package com.example.relacion_n_a_n.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.relacion_n_a_n.demo.models.EstudioModel;

public interface EstudioService {

    EstudioModel createEstudio(EstudioModel estudio);

    List<EstudioModel> allEstudios();

    Optional<EstudioModel> findByName(String nombre);

    Optional<EstudioModel> findById(Long id);
}
