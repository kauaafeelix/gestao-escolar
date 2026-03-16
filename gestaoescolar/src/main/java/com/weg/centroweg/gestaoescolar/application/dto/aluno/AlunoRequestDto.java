package com.weg.centroweg.gestaoescolar.application.dto.aluno;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AlunoRequestDto(

        @Schema(description = "Nome do aluno", example = "Kauã Felix", required = true)
        @NotBlank(message = "O nome do aluno é obrigatório.")
        String nome,

        @Schema(description = "Email do aluno", example = "kauafelix@gmail.com", required = true)
        @NotBlank(message = "O email do aluno é obrigatório.")
        @Email(message = "O email do aluno deve ser válido.")
        String email,

        @Schema(description = "Matrícula do aluno", example = "20240001", required = true)
        @NotBlank(message = "A matrícula do aluno é obrigatória.")
        String matricula,

        @Schema(description = "Data de nascimento do aluno", example = "2007-08-06", required = true)
        @Past (message = "A data de nascimento do aluno deve ser no passado.")
        LocalDate dataNascimento
) {
}
