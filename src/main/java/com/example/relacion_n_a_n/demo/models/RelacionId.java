package com.example.relacion_n_a_n.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelacionId {

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "estudio_id")
    private Long estudioId;

}
