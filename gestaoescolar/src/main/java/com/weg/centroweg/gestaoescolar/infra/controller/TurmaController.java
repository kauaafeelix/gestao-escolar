package com.weg.centroweg.gestaoescolar.infra.controller;

import com.weg.centroweg.gestaoescolar.application.dto.aluno.AlunoResponseDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaAlunoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.TurmaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public TurmaResponseDto save(@RequestBody TurmaRequestDto turmaRequestDto) {
        return turmaService.save(turmaRequestDto);
    }

    @GetMapping
    public List<TurmaResponseDto> findAll() {
        return turmaService.findAll();
    }

    @GetMapping("/{id}")
    public TurmaResponseDto findById(@PathVariable int id) {
        return turmaService.findById(id);
    }

    @PutMapping("/{id}")
    public TurmaResponseDto update(@RequestBody TurmaRequestDto turmaRequestDto, @PathVariable int id) {
        return turmaService.update(turmaRequestDto, id);
    }

    @GetMapping("/curso/{cursoId}")
    public List<TurmaResponseDto> findTurmasByCursoId(@PathVariable int cursoId) {
        return turmaService.findTurmasByCursoId(cursoId);
    }

    @GetMapping("/aluno/{turmaId}")
    public List<AlunoResponseDto> findAlunosByTurmaId(@PathVariable int turmaId) {
        return turmaService.findAlunosByTurmaId(turmaId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        turmaService.delete(id);
    }

    @PostMapping("/aluno")
    public void vincularAluno(@RequestBody TurmaAlunoRequestDto dto) {
        turmaService.vincularAluno(dto);
    }
}
