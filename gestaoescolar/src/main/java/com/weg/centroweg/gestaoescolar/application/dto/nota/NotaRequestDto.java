package com.weg.centroweg.gestaoescolar.application.dto.nota;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NotaRequestDto(
        @Positive(message = "O ID do aluno deve ser um número positivo.")
        @NotNull(message = "O ID do aluno é obrigatório.")
        @Schema(description = "ID do aluno associado à nota", example = "1", required = true)
        int alunoId,

        @Positive(message = "O ID da aula deve ser um número positivo.")
        @NotNull(message = "O ID da aula é obrigatório.")
        @Schema(description = "ID da aula associada à nota", example = "1", required = true)
        int aulaId,

        @NotNull(message = "O valor da nota é obrigatório.")
        @Schema(description = "Valor da nota", example = "8.5", required = true)
        double valor
) {
}
