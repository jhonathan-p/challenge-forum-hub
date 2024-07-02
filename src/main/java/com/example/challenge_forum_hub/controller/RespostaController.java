package com.example.challenge_forum_hub.controller;

import com.example.challenge_forum_hub.dto.*;
import com.example.challenge_forum_hub.model.Resposta;
import com.example.challenge_forum_hub.model.Topico;
import com.example.challenge_forum_hub.service.RespostaService;
import com.example.challenge_forum_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("topicos/{topicoId}/respostas")
public class RespostaController {

    @Autowired
    RespostaService respostaService;

    @GetMapping()
    public ResponseEntity respostaListarTodos(@PathVariable Topico topicoId){
        var respostas = respostaService.respostaListarTodos(topicoId);
        return ResponseEntity.ok(respostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity respostaListar(@PathVariable Topico topicoId, @PathVariable Long id){
        var resposta = respostaService.respostaListar(topicoId, id);
        return ResponseEntity.ok().body(resposta);
    }

    @PostMapping
    public ResponseEntity respostaNovo(@PathVariable Topico topicoId, @RequestBody @Valid RespostaNovoDTO respostaNovoDTO) {
        var resposta = respostaService.respostaNovo(topicoId, respostaNovoDTO);
        var uri = URI.create("/topicos/" + topicoId.getId() + "/respostas/" + resposta.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity respostaAtualizar(@PathVariable Topico topicoId, @PathVariable Long id, @RequestBody RespostaAtualizarDTO respostaAtualizarDTO){
        var resposta = respostaService.respostaAtualizar(topicoId, id, respostaAtualizarDTO);
        return ResponseEntity.ok().body(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity respostaDeletar(@PathVariable Topico topicoId, @PathVariable Long id){
        respostaService.respostaDeletar(topicoId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/solucao")
    public ResponseEntity respostaSolucao(@PathVariable Topico topicoId, @PathVariable Long id) {
        respostaService.respostaSolucao(topicoId, id);
        return ResponseEntity.ok().build();
    }

}
