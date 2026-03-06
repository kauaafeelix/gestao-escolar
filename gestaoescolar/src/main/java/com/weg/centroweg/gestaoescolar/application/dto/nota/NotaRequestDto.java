package com.weg.centroweg.gestaoescolar.application.dto.nota;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NotaRequestDto(
        @Positive(message = "O ID do aluno deve ser um número positivo.")
        @NotNull(message = "O ID do aluno é obrigatório.")
        int alunoId,

        @Positive(message = "O ID da aula deve ser um número positivo.")
        @NotNull(message = "O ID da aula é obrigatório.")
        int aulaId,

        @NotNull(message = "O valor da nota é obrigatório.")
        double valor
) {
}
