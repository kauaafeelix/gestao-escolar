package com.weg.centroweg.gestaoescolar.application.service;

import com.weg.centroweg.gestaoescolar.application.dto.aula.AulaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.aula.AulaResponseDto;
import com.weg.centroweg.gestaoescolar.application.mapper.AulaMapper;
import com.weg.centroweg.gestaoescolar.domain.entity.Aula;
import com.weg.centroweg.gestaoescolar.infra.persistence.AulaRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AulaService {

        private final AulaRepositoryImpl repository;
        private final AulaMapper mapper;

        public AulaService(AulaRepositoryImpl repository, AulaMapper mapper) {
            this.repository = repository;
            this.mapper = mapper;
        }

        public AulaResponseDto save (AulaRequestDto aulaRequestDto) {
            try {
                Aula aula = mapper.toEntity(aulaRequestDto);
                repository.save(aula);

                return mapper.toDto(aula);

            } catch (SQLException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        public List<AulaResponseDto> findAll (){
            try {
                return repository.findAll().stream().map(mapper::toDto).toList();
            } catch (SQLException e) {
                throw new IllegalArgumentException("Ocorreu um erro ao listar todas as aulas.");
            }
        }

        public AulaResponseDto findById(int id){

            try{
                Aula aula = repository.findById(id);
                return mapper.toDto(aula);

            } catch (SQLException e) {
                throw new IllegalArgumentException("Ocorreu um erro ao buscar uma aula por ID.");
            }
        }

        public AulaResponseDto update (AulaRequestDto aulaRequestDto, int id){

            try{
                Aula aula = repository.findById(id);

                if (aula == null){
                    throw new IllegalArgumentException("Nenhum aula encontrado.");
                }

                if(aulaRequestDto.turmaId() <= 0 || aulaRequestDto.dataHora() == null || aulaRequestDto.assunto() == null){
                    throw new IllegalArgumentException("O id da turma, data e hora ou assunto não podem ser nulos");
                }

                aula.setTurmaId(aulaRequestDto.turmaId());
                aula.setDataHora(aulaRequestDto.dataHora());
                aula.setAssunto(aulaRequestDto.assunto());

                repository.update(aula);
                return mapper.toDto(aula);

            }catch (SQLException e){
                throw new IllegalArgumentException("Ocorreu um erro ao atualizar uma aula.");
            }
        }

        public void delete (int id){

            try{
                Aula aula = repository.findById(id);

                if (aula == null){
                    throw new IllegalArgumentException("A Aula não existe");
                }
                repository.delete(id);

            }catch (SQLException e){
                throw new IllegalArgumentException("Ocorreu um erro ao deletar o aula de ID "+id);
            }

        }
}
