package com.weg.centroweg.gestaoescolar.application.dto.aula;

import java.sql.Timestamp;

public record AulaRequestDto(
        int turmaId,
        Timestamp dataHora,
        String assunto
) {
}
