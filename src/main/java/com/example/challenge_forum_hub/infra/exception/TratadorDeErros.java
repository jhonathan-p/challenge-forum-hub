package com.example.challenge_forum_hub.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErroValidacaoDTO::new).toList());
    }

    @ExceptionHandler(Erro400Exception.class)
    public ResponseEntity tratarErro400(Erro400Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Erro401Exception.class)
    public ResponseEntity tratarErro401(Erro401Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Erro404Exception.class)
    public ResponseEntity tratarErro404(Erro404Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Erro409Exception.class)
    public ResponseEntity tratarErro409(Erro409Exception ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(Erro500Exception.class)
    public ResponseEntity tratarErro500(Erro500Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroLogin(BadCredentialsException ex){
        return ResponseEntity.badRequest().body("Usuário inexistente ou senha inválida.");
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity tratarErroLogin(InternalAuthenticationServiceException ex){
        return ResponseEntity.badRequest().body("Usuário inexistente ou senha inválida.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErroGeral() {
        return ResponseEntity.badRequest().body("Erro inesperado, entre em contato com o suporte.");
    }

    private record ErroValidacaoDTO(String campo, String mensagem) {
        public ErroValidacaoDTO(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
