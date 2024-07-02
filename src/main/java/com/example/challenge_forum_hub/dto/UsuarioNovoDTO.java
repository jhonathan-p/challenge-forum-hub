package com.example.challenge_forum_hub.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioNovoDTO(

        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String senha) {}
