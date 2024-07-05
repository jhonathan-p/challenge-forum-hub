package com.example.challenge_forum_hub.dto;

import com.example.challenge_forum_hub.model.Resposta;

public record RespostaRetornoDTO(

        Long id,

        String mensagem,

        String dataCriacao,

        String autor) {

        public RespostaRetornoDTO(Resposta resposta) {
                this(resposta.getId(), resposta.getMensagem(), resposta.getDataCriacao(), resposta.getAutor());
        }

}