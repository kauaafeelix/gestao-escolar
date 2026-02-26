package com.weg.centroweg.gestaoescolar.application.mapper;

import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaResponseDto;
import com.weg.centroweg.gestaoescolar.domain.entity.Turma;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    public Turma toEntity (TurmaRequestDto turmaRequestDto){

        return new Turma(
                turmaRequestDto.nome(),
                turmaRequestDto.cursoId(),
                turmaRequestDto.professorId()
        );
    }

    public TurmaResponseDto toDto (Turma turma){

        return new TurmaResponseDto(
                turma.getId(),
                turma.getNome(),
                turma.getCursoId(),
                turma.getProfessorId()
        );
    }
}
