package com.example.relacion_n_a_n.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.relacion_n_a_n.demo.models.EstudioModel;

public interface EstudioRepository extends JpaRepository<EstudioModel, Long> {

    Optional<EstudioModel> findByNombre(String nombre);

}
