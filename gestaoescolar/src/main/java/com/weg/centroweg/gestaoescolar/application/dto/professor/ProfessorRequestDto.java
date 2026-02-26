package com.weg.centroweg.gestaoescolar.application.dto.professor;

public record ProfessorRequestDto(
        String nome,
        String email,
        String disciplina
) {
}
