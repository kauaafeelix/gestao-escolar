package com.weg.centroweg.gestaoescolar.application.dto.curso;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CursoRequestDto(

        @NotBlank(message = "O nome do curso é obrigatório")
        @Schema(description = "Nome do curso", example = "Desenvolvimento de Sistemas", required = true)
        String nome,

        @NotBlank(message = "O código do curso é obrigatório")
        @Schema(description = "Código do curso", example = "MIDS78", required = true)
        String codigo
) {
}
