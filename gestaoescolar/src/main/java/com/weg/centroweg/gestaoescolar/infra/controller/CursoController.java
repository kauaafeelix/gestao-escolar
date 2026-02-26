package com.weg.centroweg.gestaoescolar.infra.controller;

import com.weg.centroweg.gestaoescolar.application.dto.curso.CursoRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.curso.CursoResponseDto;
import com.weg.centroweg.gestaoescolar.application.dto.turma.TurmaResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.CursoService;
import com.weg.centroweg.gestaoescolar.application.service.TurmaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService;
    private final TurmaService turmaService;

    public CursoController(CursoService cursoService, TurmaService turmaService) {
        this.cursoService = cursoService;
        this.turmaService = turmaService;
    }

    @PostMapping
    public CursoResponseDto save(@RequestBody CursoRequestDto cursoRequestDto) {
        return cursoService.save(cursoRequestDto);
    }

    @GetMapping
    public List<CursoResponseDto> findAll() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    public CursoResponseDto findById(@PathVariable int id) {
        return cursoService.findById(id);
    }

    @GetMapping("/{id}/turmas")
    public List<TurmaResponseDto> findTurmasByCursoId(@PathVariable int id) {
        return turmaService.findTurmasByCursoId(id);
    }

    @PutMapping("/{id}")
    public CursoResponseDto update(@RequestBody CursoRequestDto cursoRequestDto, @PathVariable int id) {
        return cursoService.update(cursoRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        cursoService.delete(id);
    }
}
