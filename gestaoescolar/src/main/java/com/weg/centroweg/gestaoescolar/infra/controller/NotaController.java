package com.weg.centroweg.gestaoescolar.infra.controller;

import com.weg.centroweg.gestaoescolar.application.dto.nota.NotaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.nota.NotaResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.NotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota")
@Tag(name = "Nota", description = "Endpoints para gerenciamento de notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @Operation(summary = "Criar uma nova nota", description = "Cria uma nova nota com os dados fornecidos")
    @ApiResponse(responseCode = "200", description = "Nota criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @PostMapping
    public NotaResponseDto save(@Valid @RequestBody NotaRequestDto notaRequestDto) {
        return notaService.save(notaRequestDto);
    }

    @Operation(summary = "Listar todas as notas", description = "Retorna uma lista de todas as notas cadastradas")
    @GetMapping
    public List<NotaResponseDto> findAll() {
        return notaService.findAll();
    }

    @Operation(summary = "Buscar nota por ID", description = "Retorna os detalhes de uma nota específica com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Nota encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Nota não encontrada com o ID fornecido")
    @GetMapping("/{id}")
    public NotaResponseDto findById(@PathVariable int id) {
        return notaService.findById(id);
    }

    @Operation(summary = "Listar notas por ID do aluno", description = "Retorna uma lista de notas associadas a um aluno específico com base no ID do aluno fornecido")
    @ApiResponse(responseCode = "200", description = "Notas encontradas com sucesso")
    @ApiResponse(responseCode = "404", description = "Aluno não encontrado com o ID fornecido")
    @GetMapping("/aluno/{alunoId}")
    public List<NotaResponseDto> findByAlunoId(@PathVariable int alunoId) {
        return notaService.findByAlunoId(alunoId);
    }

    @Operation(summary = "Atualizar uma nota", description = "Atualiza os dados de uma nota existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Nota atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @ApiResponse(responseCode = "404", description = "Nota não encontrada com o ID fornecido")
    @PutMapping("/{id}")
    public NotaResponseDto update(@Valid @RequestBody NotaRequestDto notaRequestDto, @PathVariable int id) {
        return notaService.update(notaRequestDto, id);
    }

    @Operation(summary = "Excluir uma nota", description = "Exclui uma nota existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Nota excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Nota não encontrada com o ID fornecido")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        notaService.delete(id);
    }
}
