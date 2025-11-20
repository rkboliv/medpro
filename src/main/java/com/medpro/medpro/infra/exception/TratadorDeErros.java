package com.medpro.medpro.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import java.util.List; // Importando a classe List


@RestControllerAdvice
public class TratadorDeErros {

    // Tratando erro de "not found" (404)
   @ExceptionHandler
(EntityNotFoundException.class)
public ResponseEntity<Void> tratarErro404() { 

        return ResponseEntity.notFound().build();
    }

    // Tratando erro de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErroValidacao(MethodArgumentNotValidException e) {
        // Pegando todos os erros de campo da exceção
        List<FieldError> erros = e.getBindingResult().getFieldErrors();
        
        // Retornando resposta com os erros
        return ResponseEntity.badRequest()
                .body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    // Record para mapear os dados de erro de validação
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
