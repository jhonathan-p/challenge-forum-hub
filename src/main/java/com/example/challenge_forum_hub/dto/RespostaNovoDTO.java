package com.example.challenge_forum_hub.dto;

import jakarta.validation.constraints.NotBlank;

public record RespostaNovoDTO(

        @NotBlank
        String mensagem,

        @NotBlank
        String autor) {}
