package com.weg.centroweg.gestaoescolar.application.service;

import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoResponseDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaAlunoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaResponseDto;
import com.weg.centroweg.gestaoescolar.application.mapper.TurmaMapper;
import com.weg.centroweg.gestaoescolar.domain.entity.Turma;
import com.weg.centroweg.gestaoescolar.domain.entity.TurmaAluno;
import com.weg.centroweg.gestaoescolar.infra.persistence.TurmaRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TurmaService {

        private final TurmaRepositoryImpl repository;
        private final TurmaMapper mapper;
        private final AlunoService alunoService;

        public TurmaService(TurmaRepositoryImpl repository, TurmaMapper mapper, AlunoService alunoService) {
            this.repository = repository;
            this.mapper = mapper;
            this.alunoService = alunoService;
        }

        public TurmaResponseDto save (TurmaRequestDto turmaRequestDto) {
            try {
                Turma turma = mapper.toEntity(turmaRequestDto);
                repository.save(turma);

                return mapper.toDto(turma);

            } catch (SQLException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        public List<TurmaResponseDto> findAll (){
            try {
                return repository.findAll().stream().map(mapper::toDto).toList();
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        }

        public TurmaResponseDto findById(int id){

            try{
                Turma turma = repository.findById(id);
                return mapper.toDto(turma);

            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        }

        public TurmaResponseDto update (TurmaRequestDto turmaRequestDto, int id){

            try{
                Turma turma = repository.findById(id);

                if (turma == null){
                    throw new IllegalArgumentException("Nenhum turma encontrado.");
                }

                if(turmaRequestDto.nome() == null || turmaRequestDto.cursoId() < 0 || turmaRequestDto.professorId() < 0){
                    throw new IllegalArgumentException("O nome, id do curso ou id da turma não podem ser nulos");
                }

                turma.setNome(turmaRequestDto.nome());
                turma.setCursoId(turmaRequestDto.cursoId());
                turma.setProfessorId(turmaRequestDto.professorId());

                repository.update(turma);
                return mapper.toDto(turma);

            }catch (SQLException e){
                throw new IllegalArgumentException("Ocorreu um erro ao atualizar um turma.");
            }
        }

    public void vincularAluno(TurmaAlunoRequestDto dto) {
        try {
            repository.saveTurmaAluno(new TurmaAluno(dto.idTurma(), dto.idAluno()));
        } catch (SQLException e) {
            throw new IllegalArgumentException("Não foi possível vincular o aluno: " + e.getMessage());
        }
    }

    public List<AlunoResponseDto> findAlunosByTurmaId(int turmaId) {
        try {
            List<Integer> alunoIds = repository.findAlunosByTurmaId(turmaId);
            return alunoIds.stream()
                    .map(alunoService::findById)
                    .toList();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ocorreu um erro ao buscar os alunos da turma: " + e.getMessage());
        }
    }

    public List<TurmaResponseDto> findTurmasByCursoId(int cursoId) {
        try {
            return repository.findTurmasByCursoId(cursoId).stream()
                    .map(mapper::toDto)
                    .toList();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ocorreu um erro ao buscar as turmas do curso: " + e.getMessage());
        }
    }

        public void delete (int id){

            try{
                Turma turma = repository.findById(id);

                if (turma == null){
                    throw new IllegalArgumentException("A Turma não existe");
                }
                repository.delete(id);

            }catch (SQLException e){
                throw new IllegalArgumentException("Ocorreu um erro ao deletar a turma de ID "+id);
            }

        }
}
