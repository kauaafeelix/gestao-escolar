package com.weg.centroweg.gestaoescolar.application.dto.turma;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record TurmaRequestDto(
        @NotBlank(message = "O nome da turma é obrigatório")
        @Schema(description = "Nome da turma", example = "Turma A", required = true)
        String nome,

        @Positive(message = "O ID do curso deve ser maior que 0.")
        @Schema(description = "ID do curso associado à turma", example = "1", required = true)
        int cursoId,

        @Positive(message = "O ID do professor deve ser maior que 0.")
        @Schema(description = "ID do professor associado à turma", example = "1", required = true)
        int professorId
) {
}
