package com.weg.centroweg.gestaoescolar.application.service;

import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoResponseDto;
import com.weg.centroweg.gestaoescolar.application.mapper.AlunoMapper;
import com.weg.centroweg.gestaoescolar.domain.entity.Aluno;
import com.weg.centroweg.gestaoescolar.infra.persistence.AlunoRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepositoryImpl repository;
    private final AlunoMapper mapper;

    public AlunoService(AlunoRepositoryImpl repository, AlunoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AlunoResponseDto save (AlunoRequestDto alunoRequestDto) {
        try {
            Aluno alunoExistente = repository.findByEmail(alunoRequestDto.email());

            if (alunoExistente != null) {
                throw new IllegalArgumentException("O e-mail " + alunoRequestDto.email() + " já está em uso.");
            }

            Aluno aluno = mapper.toEntity(alunoRequestDto);
            repository.save(aluno);

            return mapper.toDto(aluno);

        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<AlunoResponseDto> findAll (){
        try {
            return repository.findAll().stream().map(mapper::toDto).toList();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public AlunoResponseDto findById(int id){

        try{
            Aluno aluno = repository.findById(id);
            return mapper.toDto(aluno);

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public AlunoResponseDto update (AlunoRequestDto alunoRequestDto, int id){

        try{
            Aluno aluno = repository.findById(id);

            if (aluno == null){
                throw new IllegalArgumentException("Nenhum aluno encontrado.");
            }

            if(alunoRequestDto.nome() == null || alunoRequestDto.email() == null || alunoRequestDto.dataNascimento() == null){
                throw new IllegalArgumentException("O nome, email ou data de nascimento não podem ser nulos");
            }

            aluno.setNome(alunoRequestDto.nome());
            aluno.setEmail(alunoRequestDto.email());
            aluno.setDataNascimento(alunoRequestDto.dataNascimento());

            repository.update(aluno);
            return mapper.toDto(aluno);
        }catch (SQLException e){
            throw new IllegalArgumentException("Ocorreu um erro ao atualizar um aluno.");
        }
    }

    public void delete (int id){

        try{
            Aluno aluno = repository.findById(id);

            if (aluno == null){
                throw new IllegalArgumentException("O Aluno não existe");
            }
            repository.delete(id);

        }catch (SQLException e){
            throw new IllegalArgumentException("Ocorreu um erro ao deletar o aluno de ID "+id);
        }

    }
}
