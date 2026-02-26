package com.weg.centroweg.gestaoescolar.application.dto.turma;

public record TurmaResponseDto(
        int id,
        String nome,
        int cursoId,
        int professorId
) {
}
