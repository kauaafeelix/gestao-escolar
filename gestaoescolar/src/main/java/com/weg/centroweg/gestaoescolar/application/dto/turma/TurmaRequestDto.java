package com.weg.centroweg.gestaoescolar.application.dto.turma;

public record TurmaRequestDto(
        String nome,
        int cursoId,
        int professorId
) {
}
