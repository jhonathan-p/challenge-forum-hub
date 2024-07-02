package com.example.challenge_forum_hub.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404(){
        return ResponseEntity.notFound().build();
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Página não encontrada");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex){
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

//    @ExceptionHandler(ValidacaoException.class)
//    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex){
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }

//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public ResponseEntity<String> tratarErro500(){
//        return ResponseEntity.badRequest().body("Usuário já cadastrado");
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity tratarErroGeral() {
//        return ResponseEntity.badRequest().body("Fudeu de vez!!!");
//    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
