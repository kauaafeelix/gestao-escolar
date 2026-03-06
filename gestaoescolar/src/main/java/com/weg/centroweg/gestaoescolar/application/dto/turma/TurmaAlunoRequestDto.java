package com.weg.centroweg.gestaoescolar.application.dto.turma;

import jakarta.validation.constraints.Positive;

public record TurmaAlunoRequestDto(

        @Positive(message = "O ID do aluno deve ser maior que 0.")
        int idAluno,

        @Positive(message = "O ID da turma deve ser maior que 0.")
        int idTurma
) {
}
