package com.weg.centroweg.gestaoescolar.application.service;


import com.weg.centroweg.gestaoescolar.application.dto.nota.NotaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.nota.NotaResponseDto;
import com.weg.centroweg.gestaoescolar.application.mapper.NotaMapper;
import com.weg.centroweg.gestaoescolar.domain.entity.Nota;
import com.weg.centroweg.gestaoescolar.infra.persistence.NotaRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotaService {

        private final NotaRepositoryImpl repository;
        private final NotaMapper mapper;

        public NotaService(NotaRepositoryImpl repository, NotaMapper mapper) {
            this.repository = repository;
            this.mapper = mapper;
        }

        public NotaResponseDto save (NotaRequestDto notaRequestDto) {
            try {
                if (notaRequestDto.valor() < 0.0 && notaRequestDto.valor() > 10.0) {
                    throw new IllegalArgumentException("A nota deve ser de 0 a 10");
                }

                Nota nota = mapper.toEntity(notaRequestDto);
                repository.save(nota);

                return mapper.toDto(nota);

            } catch (SQLException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        public List<NotaResponseDto> findAll (){
            try {
                return repository.findAll().stream().map(mapper::toDto).toList();
            } catch (SQLException e) {
                throw new IllegalArgumentException("Ocorreu um erro ao mostrar a lista de notas");
            }
        }

        public NotaResponseDto findById(int id){

            try{
                Nota nota = repository.findById(id);
                return mapper.toDto(nota);

            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        }

        public NotaResponseDto update (NotaRequestDto notaRequestDto, int id){

            try{
                Nota nota = repository.findById(id);

                if (nota == null){
                    throw new IllegalArgumentException("Nenhum nota encontrado.");
                }

                if(notaRequestDto.alunoId() == 0 || notaRequestDto.aulaId() == 0 || notaRequestDto.valor() < 0){
                    throw new IllegalArgumentException("O id do aluno, id da aula ou o valor da nota não podem ser nulos");
                }

                nota.setAlunoId(notaRequestDto.alunoId());
                nota.setAulaId(notaRequestDto.aulaId());
                nota.setValor(notaRequestDto.valor());

                repository.update(nota);
                return mapper.toDto(nota);

            }catch (SQLException e){
                throw new IllegalArgumentException("Ocorreu um erro ao atualizar uma nota.");
            }
        }

        public void delete (int id){

            try{
                Nota nota = repository.findById(id);

                if (nota == null){
                    throw new IllegalArgumentException("A Nota não existe");
                }
                repository.delete(id);

            }catch (SQLException e){
                throw new IllegalArgumentException("Ocorreu um erro ao deletar a nota de ID "+id);
            }

        }
}
