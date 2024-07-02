package com.example.challenge_forum_hub.service;

import com.example.challenge_forum_hub.dto.TopicoAtualizarDTO;
import com.example.challenge_forum_hub.dto.TopicoListarDTO;
import com.example.challenge_forum_hub.dto.TopicoNovoDTO;
import com.example.challenge_forum_hub.model.Topico;
import com.example.challenge_forum_hub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

    public List<TopicoListarDTO> topicoListarTodos() {
        var topicos = topicoRepository.findAll();
        return topicos.stream()
                .map(TopicoListarDTO::new)
                .collect(Collectors.toList());
    }

    public TopicoListarDTO topicoListar(Long id) {
        var topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado."));
        return new TopicoListarDTO(topico);
    }

    @Transactional
    public Topico topicoNovo(TopicoNovoDTO topicoNovoDTO){
        var topico = new Topico();
        topico.setTitulo(topicoNovoDTO.titulo());
        topico.setMensagem(topicoNovoDTO.mensagem());
        topico.setDataCriacao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        topico.setStatus("Aberto");
        topico.setAutor(topicoNovoDTO.autor());
        topico.setCurso(topicoNovoDTO.curso());
        return topicoRepository.save(topico);
    }

    @Transactional
    public TopicoAtualizarDTO topicoAtualizar(Long id, TopicoAtualizarDTO topicoAtualizarDTO) {
        var topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado."));
        if (topicoAtualizarDTO.titulo() != null) topico.setTitulo(topicoAtualizarDTO.titulo());
        if (topicoAtualizarDTO.mensagem() != null) topico.setMensagem(topicoAtualizarDTO.mensagem());
        if (topicoAtualizarDTO.curso() != null) topico.setCurso(topicoAtualizarDTO.curso());
        return new TopicoAtualizarDTO(topico);
    }

    @Transactional
    public void topicoDeletar(Long id) {
        var topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topicoRepository.deleteById(id);
        } else throw new RuntimeException("Tópico não encontrado.");
    }

}