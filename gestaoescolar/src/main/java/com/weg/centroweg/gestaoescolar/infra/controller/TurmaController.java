package com.weg.centroweg.gestaoescolar.infra.controller;

import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoResponseDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaAlunoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @Operation(summary = "Criar uma nova turma", description = "Cria uma nova turma com os dados fornecidos")
    @ApiResponse(responseCode = "200", description = "Turma criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @PostMapping
    public TurmaResponseDto save(@Valid @RequestBody TurmaRequestDto turmaRequestDto) {
        return turmaService.save(turmaRequestDto);
    }

    @Operation(summary = "Listar todas as turmas", description = "Retorna uma lista de todas as turmas cadastradas")
    @GetMapping
    public List<TurmaResponseDto> findAll() {
        return turmaService.findAll();
    }

    @Operation(summary = "Buscar turma por ID", description = "Retorna os detalhes de uma turma específica com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Turma encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Turma não encontrada com o ID fornecido")
    @GetMapping("/{id}")
    public TurmaResponseDto findById(@PathVariable int id) {
        return turmaService.findById(id);
    }

    @Operation(summary = "Atualizar uma turma", description = "Atualiza os dados de uma turma existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Turma atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @ApiResponse(responseCode = "404", description = "Turma não encontrada com o ID fornecido")
    @PutMapping("/{id}")
    public TurmaResponseDto update(@Valid @RequestBody TurmaRequestDto turmaRequestDto, @PathVariable int id) {
        return turmaService.update(turmaRequestDto, id);
    }

    @Operation(summary = "Listar turmas por ID do curso", description = "Retorna uma lista de turmas associadas a um curso específico com base no ID do curso fornecido")
    @ApiResponse(responseCode = "200", description = "Turmas encontradas com sucesso")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado com o ID fornecido")
    @GetMapping("/curso/{cursoId}")
    public List<TurmaResponseDto> findTurmasByCursoId(@PathVariable int cursoId) {
        return turmaService.findTurmasByCursoId(cursoId);
    }

    @Operation(summary = "Listar alunos por ID da turma", description = "Retorna uma lista de alunos associadas a uma turma específica com base no ID da turma fornecido")
    @ApiResponse(responseCode = "200", description = "Alunos encontrados com sucesso")
    @ApiResponse(responseCode = "404", description = "Turma não encontrada com o ID fornecido")
    @GetMapping("/aluno/{turmaId}")
    public List<AlunoResponseDto> findAlunosByTurmaId(@PathVariable int turmaId) {
        return turmaService.findAlunosByTurmaId(turmaId);
    }

    @Operation(summary = "Excluir uma turma", description = "Exclui uma turma existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Turma excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Turma não encontrada com o ID fornecido")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        turmaService.delete(id);
    }


    @Operation(summary = "Vincular um aluno a uma turma", description = "Vincula um aluno a uma turma existente com base nos IDs fornecidos")
    @ApiResponse(responseCode = "200", description = "Aluno vinculado à turma com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @ApiResponse(responseCode = "404", description = "Turma ou aluno não encontrado com os IDs fornecidos")
    @PostMapping("/aluno")
    public void vincularAluno(@Valid @RequestBody TurmaAlunoRequestDto dto) {
        turmaService.vincularAluno(dto);
    }
}
