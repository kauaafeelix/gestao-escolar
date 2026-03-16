package com.weg.centroweg.gestaoescolar.application.dto.nota;

import io.swagger.v3.oas.annotations.media.Schema;

public record NotaResponseDto(
        @Schema(description = "ID da nota", example = "1")
        int id,

        @Schema(description = "ID do aluno associado à nota", example = "1")
        int alunoId,

        @Schema(description = "ID da aula associada à nota", example = "1")
        int aulaId,

        @Schema(description = "Valor da nota", example = "8.5")
        double valor
) {
}
