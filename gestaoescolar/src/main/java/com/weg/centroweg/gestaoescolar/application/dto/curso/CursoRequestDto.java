package com.weg.centroweg.gestaoescolar.application.dto.curso;

import jakarta.validation.constraints.NotBlank;

public record CursoRequestDto(

        @NotBlank(message = "O nome do curso é obrigatório")
        String nome,

        @NotBlank(message = "O código do curso é obrigatório")
        String codigo
) {
}
