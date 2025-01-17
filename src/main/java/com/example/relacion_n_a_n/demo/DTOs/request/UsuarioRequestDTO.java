package com.example.relacion_n_a_n.demo.DTOs.request;

import jakarta.validation.constraints.Email;
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
public class UsuarioRequestDTO {

    @NotNull(message = "Los nombres no pueden ser nulos")
    @Size(min = 3, max = 50, message = "Los nombres deben tener entre 3 y 50 caracteres")
    private String nombres;

    @NotNull(message = "Los apellidos no pueden ser nulos")
    @Size(min = 3, max = 50, message = "Los apellidos deben tener entre 3 y 50 caracteres")
    private String apellidos;

    @NotNull(message = "El email no puede ser nulo")
    @Email(message = "Debe ser una dirección de correo válida")
    private String email;

    @NotNull(message = "El celular no puede ser nulo")
    @Size(min = 10, max = 15, message = "El celular debe tener entre 10 y 15 caracteres")
    private String celular;

    private String estado;
}
