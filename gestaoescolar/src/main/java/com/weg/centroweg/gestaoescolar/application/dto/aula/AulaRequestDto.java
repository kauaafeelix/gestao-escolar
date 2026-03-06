package com.weg.centroweg.gestaoescolar.application.dto.aula;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;

public record AulaRequestDto(
        @Positive(message = "O ID da turma deve ser um número positivo.")
        int turmaId,

        @NotNull
        Timestamp dataHora,

        @NotNull
        String assunto
) {
}
