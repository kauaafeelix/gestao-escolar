package com.weg.centroweg.gestaoescolar.application.dto.turma;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

public record TurmaAlunoRequestDto(

        @Positive(message = "O ID do aluno deve ser maior que 0.")
        @Schema(description = "ID do aluno a ser associado à turma", example = "1", required = true)
        int idAluno,

        @Positive(message = "O ID da turma deve ser maior que 0.")
        @Schema(description = "ID da turma à qual o aluno será associado", example = "1", required = true)
        int idTurma
) {
}
