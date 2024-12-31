package com.example.relacion_n_a_n.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.relacion_n_a_n.demo.models.RelacionModel;

@Repository
public interface RealacionRepository extends JpaRepository<RelacionModel, Long> {

    @Query("SELECT r FROM RelacionModel r WHERE r.usuario.usuario_id = :usuarioId")
    List<RelacionModel> findByUsuarioId(@Param("usuarioId") Long usuarioId);

}
