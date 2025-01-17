package com.example.relacion_n_a_n.demo.DTOs.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstudioRequestDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    private Integer horas;

    private LocalDateTime fechaInicio; // "yyyy-MM-ddTHH:mm:ss"

    private LocalDateTime fechaFin;
}
