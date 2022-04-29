package br.com.meli.projetointegrador.exception;

public class InexistentInboundOrderException extends RuntimeException {
    public InexistentInboundOrderException(String message) {
        super(message);
    }
}
