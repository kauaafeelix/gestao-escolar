package com.weg.centroweg.gestaoescolar.infra.controller;

import com.weg.centroweg.gestaoescolar.application.dto.curso.CursoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.curso.CursoResponseDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.CursoService;
import com.weg.centroweg.gestaoescolar.application.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
@Tag(name = "Curso", description = "Endpoints para gerenciamento de cursos")

public class CursoController {

    private final CursoService cursoService;
    private final TurmaService turmaService;

    public CursoController(CursoService cursoService, TurmaService turmaService) {
        this.cursoService = cursoService;
        this.turmaService = turmaService;
    }

    @Operation(summary = "Criar um novo curso", description = "Cria um novo curso com os dados fornecidos")
    @ApiResponse(responseCode = "200", description = "Curso criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @PostMapping
    public CursoResponseDto save(@Valid @RequestBody CursoRequestDto cursoRequestDto) {
        return cursoService.save(cursoRequestDto);
    }

    @Operation(summary = "Listar todos os cursos", description = "Retorna uma lista de todos os cursos cadastrados")
    @GetMapping
    public List<CursoResponseDto> findAll() {
        return cursoService.findAll();
    }

    @Operation(summary = "Buscar curso por ID", description = "Retorna os detalhes de um curso específico com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Curso encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado com o ID fornecido")
    @GetMapping("/{id}")
    public CursoResponseDto findById(@PathVariable int id) {
        return cursoService.findById(id);
    }

    @Operation(summary = "Listar turmas por ID do curso", description = "Retorna uma lista de turmas associadas a um curso específico com base no ID do curso fornecido")
    @ApiResponse(responseCode = "200", description = "Turmas encontradas com sucesso")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado com o ID fornecido")
    @GetMapping("/{id}/turmas")
    public List<TurmaResponseDto> findTurmasByCursoId(@PathVariable int id) {
        return turmaService.findTurmasByCursoId(id);
    }

    @Operation(summary = "Atualizar um curso", description = "Atualiza os dados de um curso existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado com o ID fornecido")
    @PutMapping("/{id}")
    public CursoResponseDto update(@Valid @RequestBody CursoRequestDto cursoRequestDto, @PathVariable int id) {
        return cursoService.update(cursoRequestDto, id);
    }

    @Operation(summary = "Excluir um curso", description = "Exclui um curso existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Curso excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Curso não encontrado com o ID fornecido")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        cursoService.delete(id);
    }
}
