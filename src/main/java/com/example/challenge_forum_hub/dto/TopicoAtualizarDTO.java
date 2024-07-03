package com.example.challenge_forum_hub.dto;

import com.example.challenge_forum_hub.model.Curso;
import com.example.challenge_forum_hub.model.Topico;

public record TopicoAtualizarDTO(

        String titulo,

        String mensagem,

        String autor,

        Curso curso) {

    public TopicoAtualizarDTO(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso());
    }
}
