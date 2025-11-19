package com.medpro.medpro.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.TratadorDeErros;

@RestControllerAdvice
public class TratadorDeErros {

 @ExceptionHandler
 (EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404(){
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<List<TratadorDeErros.DadosErroValidacao>>tratarErro404(
        MethodArgumentNotValidException e){
            var erros = e.getFieldError();
            return ResponseEntity.badRequest()
        .body(errors.stream().map(DadosErroValidacao::new).toList());
        }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(),erro.getDefaultMessage());
        }
    }
}
