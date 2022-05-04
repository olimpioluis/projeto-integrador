package br.com.meli.projetointegrador.exception;

public class InexistentCartException extends RuntimeException {
    public InexistentCartException(String message) {
        super(message);
    }
}
