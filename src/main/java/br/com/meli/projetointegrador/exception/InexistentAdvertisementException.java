package br.com.meli.projetointegrador.exception;

public class InexistentAdvertisementException extends RuntimeException {
    public InexistentAdvertisementException(String message) {
        super(message);
    }
}
