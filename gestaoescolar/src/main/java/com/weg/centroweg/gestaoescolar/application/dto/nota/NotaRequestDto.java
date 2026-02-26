package com.weg.centroweg.gestaoescolar.application.dto.nota;

public record NotaRequestDto(
        int alunoId,
        int aulaId,
        double valor
) {
}
