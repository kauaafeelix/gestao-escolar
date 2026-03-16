package com.weg.centroweg.gestaoescolar.application.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;

public record AulaRequestDto(
        @Positive(message = "O ID da turma deve ser um número positivo.")
        @Schema(description = "ID da turma associada à aula", example = "1", required = true)
        int turmaId,

        @NotNull
        @Schema(description = "Data e hora da aula", example = "2024-09-01T08:00:00Z", required = true)
        Timestamp dataHora,

        @NotNull
        @Schema(description = "Assunto da aula", example = "Introdução à Programação", required = true)
        String assunto
) {
}
