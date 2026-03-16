package com.weg.centroweg.gestaoescolar.infra.controller;

import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@Tag(name = "Aluno", description = "Endpoints para gerenciamento de alunos")

public class AlunoController {


    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @Operation(summary = "Criar um novo aluno", description = "Cria um novo aluno com os dados fornecidos")
    @ApiResponse(responseCode = "200", description = "Aluno criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @PostMapping
    public AlunoResponseDto save(@Valid  @RequestBody AlunoRequestDto alunoRequestDto) {
        return alunoService.save(alunoRequestDto);
    }

    @Operation(summary = "Listar todos os alunos", description = "Retorna uma lista de todos os alunos cadastrados")
    @GetMapping
    public List<AlunoResponseDto> findAll() {
        return alunoService.findAll();
    }


    @Operation(summary = "Buscar aluno por ID", description = "Retorna os detalhes de um aluno específico com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Aluno encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Aluno não encontrado com o ID fornecido")
    @GetMapping("/{id}")
    public AlunoResponseDto findById(@PathVariable int id) {
        return alunoService.findById(id);
    }

    @Operation(summary = "Atualizar um aluno", description = "Atualiza os dados de um aluno existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @ApiResponse(responseCode = "404", description = "Aluno não encontrado com o ID fornecido")
    @PutMapping("/{id}")
    public AlunoResponseDto update(@Valid @RequestBody AlunoRequestDto alunoRequestDto, @PathVariable int id) {
        return alunoService.update(alunoRequestDto, id);
    }

    @Operation(summary = "Excluir um aluno", description = "Exclui um aluno existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Aluno excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Aluno não encontrado com o ID fornecido")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        alunoService.delete(id);
    }
}
