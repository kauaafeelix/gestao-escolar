package com.weg.centroweg.gestaoescolar.application.dto.aula;

import java.sql.Timestamp;

public record AulaResponseDto(
        int id,
        int turmaId,
        Timestamp dataHora,
        String assunto
) {
}
