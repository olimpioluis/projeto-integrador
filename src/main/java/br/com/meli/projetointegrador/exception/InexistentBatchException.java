package br.com.meli.projetointegrador.exception;

public class InexistentBatchException extends RuntimeException {
    public InexistentBatchException(String message) {
        super(message);
    }
}
