package com.weg.centroweg.gestaoescolar.infra.controller;

import com.weg.centroweg.gestaoescolar.application.dto.nota.NotaRequestDto;
import com.weg.centroweg.gestaoescolar.application.dto.nota.NotaResponseDto;
import com.weg.centroweg.gestaoescolar.application.service.NotaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public NotaResponseDto save(@RequestBody NotaRequestDto notaRequestDto) {
        return notaService.save(notaRequestDto);
    }

    @GetMapping
    public List<NotaResponseDto> findAll() {
        return notaService.findAll();
    }

    @GetMapping("/{id}")
    public NotaResponseDto findById(@PathVariable int id) {
        return notaService.findById(id);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<NotaResponseDto> findByAlunoId(@PathVariable int alunoId) {
        return notaService.findByAlunoId(alunoId);
    }

    @PutMapping("/{id}")
    public NotaResponseDto update(@RequestBody NotaRequestDto notaRequestDto, @PathVariable int id) {
        return notaService.update(notaRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        notaService.delete(id);
    }
}
