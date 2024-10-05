package br.com.fourcamp.api_locadora.domain.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String mensagem){
        super(mensagem);
    }
}
