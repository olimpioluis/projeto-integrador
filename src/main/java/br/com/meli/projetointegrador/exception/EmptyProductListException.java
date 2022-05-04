package br.com.meli.projetointegrador.exception;

public class EmptyProductListException extends RuntimeException{
    public EmptyProductListException(String message) {
        super(message);
    }
}
