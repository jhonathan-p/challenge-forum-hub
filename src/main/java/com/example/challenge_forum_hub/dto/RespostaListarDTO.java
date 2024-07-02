package com.example.challenge_forum_hub.dto;

import com.example.challenge_forum_hub.model.Resposta;

public record RespostaListarDTO(

        String mensagem,

        String dataCriacao,

        String autor,

        Boolean solucao) {

    public RespostaListarDTO(Resposta resposta){
        this(resposta.getMensagem(), resposta.getDataCriacao(), resposta.getAutor(), resposta.getSolucao());
    }
}
