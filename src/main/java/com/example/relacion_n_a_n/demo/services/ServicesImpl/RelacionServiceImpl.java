package com.example.relacion_n_a_n.demo.services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.relacion_n_a_n.demo.models.RelacionModel;
import com.example.relacion_n_a_n.demo.repositories.RealacionRepository;
import com.example.relacion_n_a_n.demo.services.RelacionService;

@Service
public class RelacionServiceImpl implements RelacionService {

    @Autowired
    private RealacionRepository relacionRepository;

    @Override
    @Transactional
    public RelacionModel createRelacion(RelacionModel relacion) {
        return relacionRepository.save(relacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RelacionModel> findByUsuarioId(long id_usuario) {
        return relacionRepository.findByUsuarioId(id_usuario);
    }
}
