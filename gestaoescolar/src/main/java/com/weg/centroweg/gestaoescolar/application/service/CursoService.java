package com.weg.centroweg.gestaoescolar.application.service;

import com.weg.centroweg.gestaoescolar.application.dto.curso.CursoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.curso.CursoResponseDto;
import com.weg.centroweg.gestaoescolar.application.mapper.CursoMapper;
import com.weg.centroweg.gestaoescolar.domain.entity.Curso;
import com.weg.centroweg.gestaoescolar.infra.persistence.CursoRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class CursoService {

        private final CursoRepositoryImpl repository;
        private final CursoMapper mapper;

        public CursoService(CursoRepositoryImpl repository, CursoMapper mapper) {
            this.repository = repository;
            this.mapper = mapper;
        }

        public CursoResponseDto save (CursoRequestDto cursoRequestDto) {
            try {
                Curso curso = mapper.toEntity(cursoRequestDto);
                repository.save(curso);

                return mapper.toDto(curso);

            } catch (SQLException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        public List<CursoResponseDto> findAll (){
            try {
                return repository.findAll().stream().map(mapper::toDto).toList();
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        }

        public CursoResponseDto findById(int id){

            try{
                Curso curso = repository.findById(id);
                return mapper.toDto(curso);

            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        }

        public CursoResponseDto update (CursoRequestDto cursoRequestDto, int id){

            try{
                Curso curso = repository.findById(id);

                if (curso == null){
                    throw new IllegalArgumentException("Nenhum curso encontrado.");
                }

                if(cursoRequestDto.nome() == null || cursoRequestDto.codigo() == null ){
                    throw new IllegalArgumentException("O nome e o código não podem ser nulos");
                }

                curso.setNome(cursoRequestDto.nome());
                curso.setCodigo(cursoRequestDto.codigo());

                repository.update(curso);
                return mapper.toDto(curso);

            }catch (SQLException e){
                throw new IllegalArgumentException("Ocorreu um erro ao atualizar um curso.");
            }
        }

        public void delete (int id){

            try{
                Curso curso = repository.findById(id);

                if (curso == null){
                    throw new IllegalArgumentException("O Curso não existe");
                }
                repository.delete(id);

            }catch (SQLException e){
                throw new IllegalArgumentException("Ocorreu um erro ao deletar o curso de ID "+id);
            }

        }
}
