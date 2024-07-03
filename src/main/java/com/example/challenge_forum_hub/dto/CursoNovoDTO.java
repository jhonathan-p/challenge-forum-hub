package com.example.challenge_forum_hub.dto;

import com.example.challenge_forum_hub.model.Curso;
import jakarta.validation.constraints.NotBlank;

public record CursoNovoDTO(

        @NotBlank
        String curso/*,

        @NotBlank
        String categoria*/) {}
