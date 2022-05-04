package br.com.meli.projetointegrador.exception;

public class StatusCodeIncorrectException extends RuntimeException {
    public StatusCodeIncorrectException(String message) {
        super(message);
    }
}
