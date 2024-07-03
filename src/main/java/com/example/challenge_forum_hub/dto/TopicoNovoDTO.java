package com.example.challenge_forum_hub.dto;

import com.example.challenge_forum_hub.model.Curso;
import jakarta.validation.constraints.NotBlank;

public record TopicoNovoDTO(

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotBlank
        String autor,

        @NotBlank
        Curso curso) {}
