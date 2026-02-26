package com.weg.centroweg.gestaoescolar.application.mapper;

import com.weg.centroweg.gestaoescolar.application.dto.aula.AulaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.aula.AulaResponseDto;
import com.weg.centroweg.gestaoescolar.domain.entity.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public Aula toEntity (AulaRequestDto aulaRequestDto){

        return new Aula(
                aulaRequestDto.turmaId(),
                aulaRequestDto.dataHora(),
                aulaRequestDto.assunto()
        );
    }

    public AulaResponseDto toDto (Aula aula){

        return new AulaResponseDto(
                aula.getId(),
                aula.getTurmaId(),
                aula.getDataHora(),
                aula.getAssunto()
        );
    }
}
