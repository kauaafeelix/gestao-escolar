package com.weg.centroweg.gestaoescolar.infra.controller;


import com.weg.centroweg.gestaoescolar.application.dto.aula.AulaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.aula.AulaResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.AulaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aula")
@Tag(name = "Aula", description = "Endpoints para gerenciamento de aulas")

public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @Operation(summary = "Criar um nova aula", description = "Cria um nova aula com os dados fornecidos")
    @ApiResponse(responseCode = "200", description = "Aula criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @PostMapping
    public AulaResponseDto save(@Valid  @RequestBody AulaRequestDto aulaRequestDto) {
        return aulaService.save(aulaRequestDto);
    }

    @Operation(summary = "Listar todas as aulas", description = "Retorna uma lista de todos as aulas cadastradas")
    @GetMapping
    public List<AulaResponseDto> findAll() {
        return aulaService.findAll();
    }

    @Operation(summary = "Buscar aula por ID", description = "Retorna os detalhes de uma aula específica com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Aula encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Aula não encontrada com o ID fornecido")
    @GetMapping("/{id}")
    public AulaResponseDto findById(@PathVariable int id) {
        return aulaService.findById(id);
    }


    @Operation(summary = "Atualizar uma aula", description = "Atualiza os dados de uma aula existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Aula atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    @ApiResponse(responseCode = "404", description = "Aula não encontrada com o ID fornecido")
    @PutMapping("/{id}")
    public AulaResponseDto update(@Valid @RequestBody AulaRequestDto aulaRequestDto, @PathVariable int id) {
        return aulaService.update(aulaRequestDto, id);
    }

    @Operation(summary = "Excluir uma aula", description = "Exclui uma aula existente com base no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Aula excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Aula não encontrada com o ID fornecido")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        aulaService.delete(id);
    }
}
