package com.weg.centroweg.gestaoescolar.application.service;

import com.weg.centroweg.gestaoescolar.application.dto.professor.ProfessorRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.professor.ProfessorResponseDto;
import com.weg.centroweg.gestaoescolar.application.mapper.ProfessorMapper;
import com.weg.centroweg.gestaoescolar.domain.entity.Professor;
import com.weg.centroweg.gestaoescolar.infra.persistence.ProfessorRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service

public class ProfessorService {

        private final ProfessorRepositoryImpl repository;
        private final ProfessorMapper mapper;

        public ProfessorService(ProfessorRepositoryImpl repository, ProfessorMapper mapper) {
            this.repository = repository;
            this.mapper = mapper;
        }

        public ProfessorResponseDto save (ProfessorRequestDto profRequestDto) {
            try {
                Professor prof = mapper.toEntity(profRequestDto);
                repository.save(prof);

                return mapper.toDto(prof);

            } catch (SQLException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        public List<ProfessorResponseDto> findAll (){
            try {
                return repository.findAll().stream().map(mapper::toDto).toList();
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        }

        public ProfessorResponseDto findById(int id){

            try{
                Professor prof = repository.findById(id);
                return mapper.toDto(prof);

            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        }

        public ProfessorResponseDto update (ProfessorRequestDto profRequestDto, int id){

            try{
                Professor prof = repository.findById(id);

                if (prof == null){
                    throw new IllegalArgumentException("Nenhum prof encontrado.");
                }

                if(profRequestDto.nome() == null || profRequestDto.email() == null || profRequestDto.disciplina() == null){
                    throw new IllegalArgumentException("O nome, email ou disciplina não podem ser nulos");
                }

                prof.setNome(profRequestDto.nome());
                prof.setEmail(profRequestDto.email());
                prof.setDisciplina(profRequestDto.disciplina());

                repository.update(prof);
                return mapper.toDto(prof);
            }catch (SQLException e){
                throw new IllegalArgumentException("Ocorreu um erro ao atualizar um prof.");
            }
        }

        public void delete (int id){

            try{
                Professor prof = repository.findById(id);

                if (prof == null){
                    throw new IllegalArgumentException("O Professor não existe");
                }
                repository.delete(id);

            }catch (SQLException e){
                throw new IllegalArgumentException("Ocorreu um erro ao deletar o prof de ID "+id);
            }

        }
}
