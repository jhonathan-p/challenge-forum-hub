package com.example.challenge_forum_hub.service;

import com.example.challenge_forum_hub.dto.RespostaAtualizarDTO;
import com.example.challenge_forum_hub.dto.RespostaListarDTO;
import com.example.challenge_forum_hub.dto.RespostaNovoDTO;
import com.example.challenge_forum_hub.model.Resposta;
import com.example.challenge_forum_hub.model.Status;
import com.example.challenge_forum_hub.model.Topico;
import com.example.challenge_forum_hub.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespostaService {

    @Autowired
    RespostaRepository respostaRepository;

    public List<RespostaListarDTO> respostaListarTodos(Topico topicoId) {
        var respostas = respostaRepository.findByTopicoId(topicoId);
        return respostas.stream()
                .map(RespostaListarDTO::new)
                .collect(Collectors.toList());
    }

    public RespostaListarDTO respostaListar(Topico topicoId, Long id) {
        var resposta = respostaRepository.findByTopicoIdAndId(topicoId, id).orElseThrow(() -> new RuntimeException("Tópico não encontrado."));
        return new RespostaListarDTO(resposta);
    }

    @Transactional
    public Resposta respostaNovo(Topico topicoId, RespostaNovoDTO respostaNovoDTO){
        if (Status.SOLUCIONADO.equals(topicoId.getStatus())) {
            throw new RuntimeException("Tópico já solucionado.");
        }
        var resposta = new Resposta();
        resposta.setTopicoId(topicoId);
        resposta.setMensagem(respostaNovoDTO.mensagem());
        resposta.setDataCriacao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        resposta.setAutor(respostaNovoDTO.autor());
        resposta.setSolucao(false);
        return respostaRepository.save(resposta);
    }

    @Transactional
    public RespostaAtualizarDTO respostaAtualizar(Topico topicoId, Long id, RespostaAtualizarDTO respostaAtualizarDTO) {
        var resposta = respostaRepository.findByTopicoIdAndId(topicoId, id).orElseThrow(() -> new RuntimeException("Tópico não encontrado."));
        if (respostaAtualizarDTO.mensagem() != null) resposta.setMensagem(respostaAtualizarDTO.mensagem());
        return new RespostaAtualizarDTO(resposta);
    }

    @Transactional
    public void respostaDeletar(Topico topicoId, Long id) {
        var resposta = respostaRepository.findByTopicoIdAndId(topicoId, id).orElseThrow(() -> new RuntimeException("Tópico não encontrado."));
        respostaRepository.deleteByTopicoIdAndId(topicoId, id);
    }

    @Transactional
    public void respostaSolucao(Topico topicoId, Long id) {
        if (respostaRepository.existsByTopicoIdAndSolucao(topicoId, true)) {
            throw new RuntimeException("Já existe uma resposta marcada como solução para este tópico.");
        }
        var resposta = respostaRepository.findByTopicoIdAndId(topicoId, id).orElseThrow(() -> new RuntimeException("Tópico não encontrado."));
        resposta.setSolucao(true);
        topicoId.setStatus(Status.SOLUCIONADO);
    }
}