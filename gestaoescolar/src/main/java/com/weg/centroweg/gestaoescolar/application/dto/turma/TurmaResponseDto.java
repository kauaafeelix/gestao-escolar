package com.weg.centroweg.gestaoescolar.application.dto.turma;

import io.swagger.v3.oas.annotations.media.Schema;

public record TurmaResponseDto(
        @Schema(description = "ID da turma", example = "1")
        int id,

        @Schema(description = "Nome da turma", example = "Turma A")
        String nome,

        @Schema(description = "ID do curso associado à turma", example = "1")
        int cursoId,

        @Schema(description = "ID do professor associado à turma", example = "1")
        int professorId
) {
}
