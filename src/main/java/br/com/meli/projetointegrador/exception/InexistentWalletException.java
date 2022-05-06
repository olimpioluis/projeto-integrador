package br.com.meli.projetointegrador.exception;

public class InexistentWalletException extends RuntimeException {
    public InexistentWalletException(String message) {
        super(message);
    }
}
