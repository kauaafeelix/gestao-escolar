package com.weg.centroweg.gestaoescolar.application.dto.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record TurmaRequestDto(
        @NotBlank(message = "O nome da turma é obrigatório")
        String nome,

        @Positive(message = "O ID do curso deve ser maior que 0.")
        int cursoId,

        @Positive(message = "O ID do professor deve ser maior que 0.")
        int professorId
) {
}
