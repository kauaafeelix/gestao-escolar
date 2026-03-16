package com.weg.centroweg.gestaoescolar.application.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;

public record AulaResponseDto(
        @Schema(description = "ID da aula", example = "1")
        int id,
        @Schema(description = "ID da turma associada à aula", example = "1")
        int turmaId,
        @Schema(description = "Data e hora da aula", example = "2024-09-01T08:00:00Z")
        Timestamp dataHora,
        @Schema(description = "Assunto da aula", example = "Introdução à Programação")
        String assunto
) {
}
