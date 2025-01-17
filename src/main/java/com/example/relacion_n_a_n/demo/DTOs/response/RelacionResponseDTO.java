package com.example.relacion_n_a_n.demo.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelacionResponseDTO {

    private Long usuarioId;
    private Long estudioId;
    private String estado;
}
