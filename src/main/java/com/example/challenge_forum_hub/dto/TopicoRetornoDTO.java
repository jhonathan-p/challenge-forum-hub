package com.example.challenge_forum_hub.dto;

import com.example.challenge_forum_hub.model.Topico;

public record TopicoRetornoDTO(

        Long id,

        String titulo,

        String mensagem,

        String dataCriacao,

        String status,

        String autor,

        String curso) {

    public TopicoRetornoDTO(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(),
                topico.getDataCriacao(), topico.getStatus().name(),
                topico.getAutor(), topico.getCurso().getCurso());
    }
}
