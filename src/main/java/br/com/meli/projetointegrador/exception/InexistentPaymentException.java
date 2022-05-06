package br.com.meli.projetointegrador.exception;

public class InexistentPaymentException extends RuntimeException {
    public InexistentPaymentException(String message) {
        super(message);
    }
}
