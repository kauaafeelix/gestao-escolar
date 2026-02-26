package com.weg.centroweg.gestaoescolar.infra.controller;

import com.weg.centroweg.gestaoescolar.application.dto.professor.ProfessorRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.professor.ProfessorResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ProfessorResponseDto save(@RequestBody ProfessorRequestDto professorRequestDto) {
        return professorService.save(professorRequestDto);
    }

    @GetMapping
    public List<ProfessorResponseDto> findAll() {
        return professorService.findAll();
    }

    @GetMapping("/{id}")
    public ProfessorResponseDto findById(@PathVariable int id) {
        return professorService.findById(id);
    }

    @PutMapping("/{id}")
    public ProfessorResponseDto update(@RequestBody ProfessorRequestDto professorRequestDto, @PathVariable int id) {
        return professorService.update(professorRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        professorService.delete(id);
    }
}
