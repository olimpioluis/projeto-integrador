package br.com.meli.projetointegrador.exception;

public class InexistentCustomerException extends RuntimeException {
    public InexistentCustomerException(String message) {
        super(message);
    }
}
