package br.com.meli.projetointegrador.exception;

public class InexistentInboundOrderException extends RuntimeException {
    public InexistentInboundOrderException(RuntimeException exception) {
        super(exception);
    }

    public InexistentInboundOrderException(String message) {
        super(message);
    }
}
