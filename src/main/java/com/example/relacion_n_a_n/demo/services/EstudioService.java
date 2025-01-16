package com.example.relacion_n_a_n.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.repositories.EstudioRepository;

@Service
public class EstudioService {

    @Autowired
    EstudioRepository estudioRepository;

    @Transactional
    public EstudioModel createEstudio(EstudioModel estudio) {
        return estudioRepository.save(estudio);
    }

    @Transactional(readOnly = true)
    public List<EstudioModel> allEstudios() {
        return estudioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<EstudioModel> findByName(String nombre) {
        return estudioRepository.findByNombre(nombre);
    }

    @Transactional(readOnly = true)
    public Optional<EstudioModel> findById(Long id) {
        return estudioRepository.findById(id);
    }

}
