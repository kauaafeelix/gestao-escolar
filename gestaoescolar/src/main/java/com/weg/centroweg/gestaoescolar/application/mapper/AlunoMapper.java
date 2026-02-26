package com.weg.centroweg.gestaoescolar.application.mapper;

import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoResponseDto;
import com.weg.centroweg.gestaoescolar.domain.entity.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno toEntity (AlunoRequestDto alunoRequestDto){

        return new Aluno(
                alunoRequestDto.nome(),
                alunoRequestDto.email(),
                alunoRequestDto.matricula(),
                alunoRequestDto.dataNascimento()
        );
    }

    public AlunoResponseDto toDto (Aluno aluno){

        return new AlunoResponseDto(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula(),
                aluno.getDataNascimento()
        );
    }
}
