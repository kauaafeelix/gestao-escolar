package com.weg.centroweg.gestaoescolar.application.dto.professor;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProfessorResponseDto(
        @Schema(description = "ID do professor", example = "1")
        int id,

        @Schema(description = "Nome do professor", example = "Carlos Fábio")
        String nome,

        @Schema(description = "Email do professor", example = "carlosfabio@gmail.com", required = true)
        String email,

        @Schema(description = "Disciplina do professor", example = "Serviços de Redes", required = true)
        String disciplina
) {
}
