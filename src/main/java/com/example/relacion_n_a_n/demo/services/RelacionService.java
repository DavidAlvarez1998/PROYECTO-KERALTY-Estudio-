package com.example.relacion_n_a_n.demo.services;

import java.util.List;

import com.example.relacion_n_a_n.demo.models.RelacionModel;

public interface RelacionService {

    RelacionModel createRelacion(RelacionModel relacion);

    List<RelacionModel> findByUsuarioId(long id_usuario);
}
