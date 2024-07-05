package com.example.challenge_forum_hub.controller;

import com.example.challenge_forum_hub.dto.RespostaAtualizarDTO;
import com.example.challenge_forum_hub.dto.RespostaNovoDTO;
import com.example.challenge_forum_hub.dto.RespostaRetornoDTO;
import com.example.challenge_forum_hub.service.RespostaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("topicos/{topicoId}/respostas")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

    @Autowired
    RespostaService respostaService;

    @GetMapping()
    public ResponseEntity respostaListarTodos(@PathVariable Long topicoId){
        var respostas = respostaService.respostaListarTodos(topicoId);
        return ResponseEntity.ok(respostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity respostaListar(@PathVariable Long topicoId, @PathVariable Long id){
        var resposta = respostaService.respostaListar(topicoId, id);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity respostaNovo(@PathVariable Long topicoId, @RequestBody @Valid RespostaNovoDTO respostaNovoDTO) {
        var resposta = respostaService.respostaNovo(topicoId, respostaNovoDTO);
        var uri = URI.create("/topicos/" + topicoId + "/respostas/" + resposta.getId());
        var retorno = new RespostaRetornoDTO(resposta);
        return ResponseEntity.created(uri).body(retorno);
    }

    @PutMapping("/{id}")
    public ResponseEntity respostaAtualizar(@PathVariable Long topicoId, @PathVariable Long id, @RequestBody RespostaAtualizarDTO respostaAtualizarDTO){
        var resposta = respostaService.respostaAtualizar(topicoId, id, respostaAtualizarDTO);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity respostaDeletar(@PathVariable Long topicoId, @PathVariable Long id){
        respostaService.respostaDeletar(topicoId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/solucao")
    public ResponseEntity respostaSolucao(@PathVariable Long topicoId, @PathVariable Long id) {
        respostaService.respostaSolucao(topicoId, id);
        return ResponseEntity.ok("Resposta marcada como solução.");
    }

}
