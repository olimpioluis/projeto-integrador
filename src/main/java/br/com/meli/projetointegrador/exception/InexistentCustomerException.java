package br.com.meli.projetointegrador.exception;

public class InexistentCustomerException extends RuntimeException {
    public InexistentCustomerException(RuntimeException exception) {
        super(exception);
    }

    public InexistentCustomerException(String message) {
        super(message);
    }
}
