package br.com.meli.projetointegrador.exception;

public class InexistentSectionException extends RuntimeException{
    public InexistentSectionException(String message) {
        super(message);
    }
}
