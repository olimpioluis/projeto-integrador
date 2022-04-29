package br.com.meli.projetointegrador.exception;

public class InexistentProductException extends RuntimeException {
    public InexistentProductException(String message) {
        super(message);
    }
}
