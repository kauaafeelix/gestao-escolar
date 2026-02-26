package com.weg.centroweg.gestaoescolar.application.mapper;


import com.weg.centroweg.gestaoescolar.application.dto.nota.NotaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.nota.NotaResponseDto;
import com.weg.centroweg.gestaoescolar.domain.entity.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public Nota toEntity (NotaRequestDto notaRequestDto){

        return new Nota(
                notaRequestDto.alunoId(),
                notaRequestDto.aulaId(),
                notaRequestDto.valor()
        );
    }

    public NotaResponseDto toDto (Nota nota){

        return new NotaResponseDto(
                nota.getId(),
                nota.getAlunoId(),
                nota.getAulaId(),
                nota.getValor()
        );
    }
}
