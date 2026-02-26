package com.weg.centroweg.gestaoescolar.application.dto.professor;

public record ProfessorResponseDto(
        int id,
        String nome,
        String email,
        String disciplina
) {
}
