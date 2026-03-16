package com.weg.centroweg.gestaoescolar.infra.controller;

import com.weg.centroweg.gestaoescolar.application.dto.professor.ProfessorRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.professor.ProfessorResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/professor")
@Tag(name = "Professor", description = "Endpoints para gerenciamento de professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @Operation(summary = "Criar um novo professor", description = "Cria um novo professor com os dados fornecidos")
    @ApiResponse(responseCode = "200", description = "Professor criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @PostMapping
    public ProfessorResponseDto save(@Valid @RequestBody ProfessorRequestDto professorRequestDto) {
        return professorService.save(professorRequestDto);
    }

    @Operation(summary = "Listar todos os professores", description = "Retorna uma lista de todos os professores cadastrados")
    @GetMapping
    public List<ProfessorResponseDto> findAll() {
        return professorService.findAll();
    }

    @Operation(summary = "Buscar professor por ID", description = "Retorna os detalhes de um professor específico com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Professor encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Professor não encontrado com o ID fornecido")
    @GetMapping("/{id}")
    public ProfessorResponseDto findById(@PathVariable int id) {
        return professorService.findById(id);
    }

    @Operation(summary = "Atualizar um professor", description = "Atualiza os dados de um professor existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @ApiResponse(responseCode = "404", description = "Professor não encontrado com o ID fornecido")
    @PutMapping("/{id}")
    public ProfessorResponseDto update(@Valid @RequestBody ProfessorRequestDto professorRequestDto, @PathVariable int id) {
        return professorService.update(professorRequestDto, id);
    }

    @Operation(summary = "Excluir um professor", description = "Exclui um professor existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Professor excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Professor não encontrado com o ID fornecido")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        professorService.delete(id);
    }
}
