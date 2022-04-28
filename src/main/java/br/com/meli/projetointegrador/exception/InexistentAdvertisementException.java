package br.com.meli.projetointegrador.exception;

public class InexistentAdvertisementException extends RuntimeException {
    public InexistentAdvertisementException(RuntimeException exception) {
        super(exception);
    }

    public InexistentAdvertisementException(String message) {
        super(message);
    }
}
