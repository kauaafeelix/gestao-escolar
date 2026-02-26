package com.weg.centroweg.gestaoescolar.application.dto.aluno;

import java.time.LocalDate;

public record AlunoResponseDto(
        int id,
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
