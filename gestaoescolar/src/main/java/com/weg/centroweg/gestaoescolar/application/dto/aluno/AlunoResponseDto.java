package com.weg.centroweg.gestaoescolar.application.dto.aluno;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record AlunoResponseDto(

        @Schema(description = "ID do aluno", example = "1")
        int id,

        @Schema(description = "Nome do aluno", example = "Kauã Felix")
        String nome,

        @Schema(description = "Email do aluno", example = "kauafelix@gmail.com", required = true)
        String email,

        @Schema(description = "Matrícula do aluno", example = "20240001", required = true)
        String matricula,

        @Schema(description = "Data de nascimento do aluno", example = "2007-08-06", required = true)
        LocalDate dataNascimento
) {
}
