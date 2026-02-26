package com.weg.centroweg.gestaoescolar.application.dto.aluno;

import java.time.LocalDate;

public record AlunoRequestDto(
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
