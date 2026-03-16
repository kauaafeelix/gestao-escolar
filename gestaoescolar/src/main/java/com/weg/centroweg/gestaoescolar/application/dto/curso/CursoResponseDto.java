package com.weg.centroweg.gestaoescolar.application.dto.curso;

import io.swagger.v3.oas.annotations.media.Schema;

public record CursoResponseDto(

        @Schema(description = "ID do curso", example = "1")
        int id,

        @Schema(description = "Nome do curso", example = "Desenvolvimento de Sistemas")
        String nome,

        @Schema(description = "Código do curso", example = "MIDS78")
        String codigo
) {
}
