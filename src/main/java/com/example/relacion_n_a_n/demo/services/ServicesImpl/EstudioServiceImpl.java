package com.example.relacion_n_a_n.demo.services.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.relacion_n_a_n.demo.models.EstudioModel;
import com.example.relacion_n_a_n.demo.repositories.EstudioRepository;
import com.example.relacion_n_a_n.demo.services.EstudioService;

@Service
public class EstudioServiceImpl implements EstudioService {

    @Autowired
    EstudioRepository estudioRepository;

    @Override
    @Transactional
    public EstudioModel createEstudio(EstudioModel estudio) {
        return estudioRepository.save(estudio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstudioModel> allEstudios() {
        return estudioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EstudioModel> findByName(String nombre) {
        return estudioRepository.findByNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EstudioModel> findById(Long id) {
        return estudioRepository.findById(id);
    }

}
