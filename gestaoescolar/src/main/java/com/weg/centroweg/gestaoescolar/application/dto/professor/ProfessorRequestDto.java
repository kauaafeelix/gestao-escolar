package com.weg.centroweg.gestaoescolar.application.dto.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequestDto(
        @NotBlank(message = "O nome do professor é obrigatório")
        String nome,

        @NotBlank(message = "O email do professor é obrigatório")
        @Email(message = "O email do professor deve ser válido")
        String email,

        @NotBlank(message = "A disciplina do professor é obrigatória")
        String disciplina
) {
}
