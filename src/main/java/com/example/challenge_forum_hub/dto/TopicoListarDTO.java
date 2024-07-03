package com.example.challenge_forum_hub.dto;

import com.example.challenge_forum_hub.model.Curso;
import com.example.challenge_forum_hub.model.Topico;

public record TopicoListarDTO(

        String titulo,

        String mensagem,

        String dataCriacao,

        String status,

        String autor,

        Curso curso) {

    public TopicoListarDTO(Topico topico){
        this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
