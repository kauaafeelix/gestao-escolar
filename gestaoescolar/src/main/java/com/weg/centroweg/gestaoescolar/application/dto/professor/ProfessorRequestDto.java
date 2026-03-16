package com.weg.centroweg.gestaoescolar.application.dto.professor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequestDto(
        @NotBlank(message = "O nome do professor é obrigatório")
        @Schema(description = "Nome do professor", example = "Carlos Fábio", required = true)
        String nome,

        @NotBlank(message = "O email do professor é obrigatório")
        @Email(message = "O email do professor deve ser válido")
        @Schema(description = "Email do professor", example = "carlosfabio@gmail.com", required = true)
        String email,

        @NotBlank(message = "A disciplina do professor é obrigatória")
        @Schema(description = "Disciplina do professor", example = "Serviços de Redes", required = true)
        String disciplina
) {
}
