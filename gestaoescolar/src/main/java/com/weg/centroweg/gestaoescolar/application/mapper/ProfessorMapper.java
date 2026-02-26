package com.weg.centroweg.gestaoescolar.application.mapper;

import com.weg.centroweg.gestaoescolar.application.dto.professor.ProfessorRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.professor.ProfessorResponseDto;
import com.weg.centroweg.gestaoescolar.domain.entity.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor toEntity (ProfessorRequestDto notaRequestDto){

        return new Professor(
                notaRequestDto.nome(),
                notaRequestDto.email(),
                notaRequestDto.disciplina()
        );
    }

    public ProfessorResponseDto toDto (Professor prof){

        return new ProfessorResponseDto(
                prof.getId(),
                prof.getNome(),
                prof.getEmail(),
                prof.getDisciplina()
        );
    }
}
