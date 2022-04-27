package br.com.meli.projetointegrador.exception;

public class InexistentSectionException extends RuntimeException{

    public InexistentSectionException(RuntimeException exception) {
        super(exception);
    }

    public InexistentSectionException(String message) {
        super(message);
    }
}
