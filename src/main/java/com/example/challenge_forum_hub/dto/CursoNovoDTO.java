package com.example.challenge_forum_hub.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoNovoDTO(

        @NotBlank
        String curso) {}
