package com.example.relacion_n_a_n.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estudios_has_usuario")
public class RelacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long relacion_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private UsuarioModel usuario;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "estudio_id", referencedColumnName = "estudio_id")
    private EstudioModel estudio;

    private String estado;

}
