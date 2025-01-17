package com.example.relacion_n_a_n.demo.DTOs.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstudioResponseDTO {

    private Long estudioId;
    private String nombre;
    private Integer horas;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
