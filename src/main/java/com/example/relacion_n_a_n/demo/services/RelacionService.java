package com.example.relacion_n_a_n.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.relacion_n_a_n.demo.models.RelacionModel;
import com.example.relacion_n_a_n.demo.repositories.RealacionRepository;

@Service
public class RelacionService {

    @Autowired
    private RealacionRepository relacionRepository;

    public RelacionModel createRelacion(RelacionModel relacion) {
        return relacionRepository.save(relacion);
    }

    public List<RelacionModel> findByUsuarioId(long id_usuario) {
        return relacionRepository.findByUsuarioId(id_usuario);
    }
}
