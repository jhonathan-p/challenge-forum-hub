package com.example.challenge_forum_hub.controller;

import com.example.challenge_forum_hub.dto.CursoAtualizarDTO;
import com.example.challenge_forum_hub.dto.CursoNovoDTO;
import com.example.challenge_forum_hub.dto.TopicoAtualizarDTO;
import com.example.challenge_forum_hub.dto.TopicoNovoDTO;
import com.example.challenge_forum_hub.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping()
    public ResponseEntity cursoListarTodos(){
        var cursos = cursoService.cursoListarTodos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity cursoListar(@PathVariable Long id){
        var curso = cursoService.cursoListar(id);
        return ResponseEntity.ok().body(curso);
    }

    @PostMapping
    public ResponseEntity cursoNovo(@RequestBody @Valid CursoNovoDTO cursoNovoDTO) {
        var curso = cursoService.cursoNovo(cursoNovoDTO);
        var uri = URI.create("/cursos/" + curso.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity cursoAtualizar(@PathVariable Long id, @RequestBody CursoAtualizarDTO cursoAtualizarDTO){
        var curso = cursoService.cursoAtualizar(id, cursoAtualizarDTO);
        return ResponseEntity.ok().body(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity cursoDeletar(@PathVariable Long id){
        cursoService.cursoDeletar(id);
        return ResponseEntity.noContent().build();
    }

}
