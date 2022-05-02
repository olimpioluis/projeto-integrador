package br.com.meli.projetointegrador.exception;

public class InexistentStockManagerException extends RuntimeException{
    public InexistentStockManagerException(String message) {
        super(message);
    }
}
