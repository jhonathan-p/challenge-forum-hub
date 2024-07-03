package com.example.challenge_forum_hub.dto;

import com.example.challenge_forum_hub.model.Categoria;
import com.example.challenge_forum_hub.model.Curso;

public record CursoListarDTO(

        String curso,

        Categoria categoria) {

    public CursoListarDTO(Curso curso){
        this(curso.getCurso(), curso.getCategoria());
    }
}
