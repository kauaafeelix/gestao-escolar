package com.weg.centroweg.gestaoescolar.application.mapper;

import com.weg.centroweg.gestaoescolar.application.dto.curso.CursoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.curso.CursoResponseDto;
import com.weg.centroweg.gestaoescolar.domain.entity.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public Curso toEntity (CursoRequestDto cursoRequestDto){

        return new Curso(
                cursoRequestDto.nome(),
                cursoRequestDto.codigo()
        );
    }

    public CursoResponseDto toDto (Curso curso){

        return new CursoResponseDto(
                curso.getId(),
                curso.getNome(),
                curso.getCodigo()
        );
    }
}
