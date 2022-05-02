package br.com.meli.projetointegrador.exception;

public class NotFoundProductException extends RuntimeException {

    public NotFoundProductException(RuntimeException exception) {
        super(exception);
    }

    public NotFoundProductException(String message) {
        super(message);
    }
}
