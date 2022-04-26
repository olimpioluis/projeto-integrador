package br.com.meli.projetointegrador.exception;

public class InexistentBatchException extends RuntimeException {
    public InexistentBatchException(RuntimeException exception) {
        super(exception);
    }

    public InexistentBatchException(String message) {
        super(message);
    }
}
