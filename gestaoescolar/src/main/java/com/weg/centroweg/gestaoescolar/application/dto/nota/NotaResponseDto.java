package com.weg.centroweg.gestaoescolar.application.dto.nota;

public record NotaResponseDto(
        int id,
        int alunoId,
        int aulaId,
        double valor
) {
}
