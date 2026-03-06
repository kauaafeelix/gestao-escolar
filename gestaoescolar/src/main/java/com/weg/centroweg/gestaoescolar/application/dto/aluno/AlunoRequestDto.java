package com.weg.centroweg.gestaoescolar.application.dto.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AlunoRequestDto(

        @NotBlank(message = "O nome do aluno é obrigatório.")
        String nome,

        @NotBlank(message = "O email do aluno é obrigatório.")
        @Email(message = "O email do aluno deve ser válido.")
        String email,

        @NotBlank(message = "A matrícula do aluno é obrigatória.")
        String matricula,

        @Past (message = "A data de nascimento do aluno deve ser no passado.")
        LocalDate dataNascimento
) {
}
