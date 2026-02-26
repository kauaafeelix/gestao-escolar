package com.weg.centroweg.gestaoescolar.infra.controller;


import com.weg.centroweg.gestaoescolar.application.dto.aula.AulaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.aula.AulaResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.AulaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aula")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping
    public AulaResponseDto save(@RequestBody AulaRequestDto aulaRequestDto) {
        return aulaService.save(aulaRequestDto);
    }

    @GetMapping
    public List<AulaResponseDto> findAll() {
        return aulaService.findAll();
    }

    @GetMapping("/{id}")
    public AulaResponseDto findById(@PathVariable int id) {
        return aulaService.findById(id);
    }

    @PutMapping("/{id}")
    public AulaResponseDto update(@RequestBody AulaRequestDto aulaRequestDto, @PathVariable int id) {
        return aulaService.update(aulaRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        aulaService.delete(id);
    }
}
