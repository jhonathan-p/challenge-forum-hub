package com.example.challenge_forum_hub.dto;

import com.example.challenge_forum_hub.model.Curso;

public record CursoAtualizarDTO(

        String curso/*,

        Categoria categoria*/) {

    public CursoAtualizarDTO(Curso curso) {
        this(curso.getCurso()/*, curso.getCategoria()*/);
    }
}
