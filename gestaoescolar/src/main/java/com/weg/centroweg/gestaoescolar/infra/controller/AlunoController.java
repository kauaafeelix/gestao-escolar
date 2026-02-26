package com.weg.centroweg.gestaoescolar.infra.controller;

import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")

public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoResponseDto save(@RequestBody AlunoRequestDto alunoRequestDto) {
        return alunoService.save(alunoRequestDto);
    }

    @GetMapping
    public List<AlunoResponseDto> findAll() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public AlunoResponseDto findById(@PathVariable int id) {
        return alunoService.findById(id);
    }

    @PutMapping("/{id}")
    public AlunoResponseDto update(@RequestBody AlunoRequestDto alunoRequestDto, @PathVariable int id) {
        return alunoService.update(alunoRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        alunoService.delete(id);
    }
}
