package com.example.challenge_forum_hub.controller;

import com.example.challenge_forum_hub.dto.TopicoAtualizarDTO;
import com.example.challenge_forum_hub.dto.TopicoNovoDTO;
import com.example.challenge_forum_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoService topicoService;

    @GetMapping()
    public ResponseEntity topicoListarTodos(){
        var topicos = topicoService.topicoListarTodos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity topicoListar(@PathVariable Long id){
        var topico = topicoService.topicoListar(id);
        return ResponseEntity.ok().body(topico);
    }

    @PostMapping
    public ResponseEntity topicoNovo(@RequestBody @Valid TopicoNovoDTO topicoNovoDTO) {
        var topico = topicoService.topicoNovo(topicoNovoDTO);
        var uri = URI.create("/topicos/" + topico.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity topicoAtualizar(@PathVariable Long id, @RequestBody TopicoAtualizarDTO topicoAtualizarDTO){
        var topico = topicoService.topicoAtualizar(id, topicoAtualizarDTO);
        return ResponseEntity.ok().body(topico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity topicoDeletar(@PathVariable Long id){
        topicoService.topicoDeletar(id);
        return ResponseEntity.noContent().build();
    }

}
