package com.example.challenge_forum_hub.service;

import com.example.challenge_forum_hub.dto.*;
import com.example.challenge_forum_hub.model.Categoria;
import com.example.challenge_forum_hub.model.Curso;
import com.example.challenge_forum_hub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<CursoListarDTO> cursoListarTodos() {
        var cursos = cursoRepository.findAll();
        return cursos.stream()
                .map(CursoListarDTO::new)
                .collect(Collectors.toList());
    }

    public CursoListarDTO cursoListar(Long id) {
        var curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado."));
        return new CursoListarDTO(curso);
    }

    @Transactional
    public Curso cursoNovo(CursoNovoDTO cursoNovoDTO){
        var curso = new Curso();
        curso.setCurso(cursoNovoDTO.curso());
        curso.setCategoria(Categoria.PROGRAMACAO);
        return cursoRepository.save(curso);
    }

    @Transactional
    public CursoAtualizarDTO cursoAtualizar(Long id, CursoAtualizarDTO cursoAtualizarDTO) {
        var curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado."));
        if (cursoAtualizarDTO.curso() != null) curso.setCurso(cursoAtualizarDTO.curso());
//        if (cursoAtualizarDTO.categoria() != null) curso.setCategoria(cursoAtualizarDTO.categoria());
        return new CursoAtualizarDTO(curso);
    }

    @Transactional
    public void cursoDeletar(Long id) {
        var curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            cursoRepository.deleteById(id);
        } else throw new RuntimeException("Curso não encontrado.");
    }

}