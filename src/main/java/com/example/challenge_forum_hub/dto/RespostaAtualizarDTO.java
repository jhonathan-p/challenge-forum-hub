package com.example.challenge_forum_hub.dto;

import com.example.challenge_forum_hub.model.Resposta;

public record RespostaAtualizarDTO(

        String mensagem) {

    public RespostaAtualizarDTO(Resposta resposta){
        this(resposta.getMensagem());
    }
}
