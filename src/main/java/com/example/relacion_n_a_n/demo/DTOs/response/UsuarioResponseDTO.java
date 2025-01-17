package com.example.relacion_n_a_n.demo.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Long usuarioId;
    private String nombres;
    private String apellidos;
    private String email;
    private String celular;
    private String estado;
}
