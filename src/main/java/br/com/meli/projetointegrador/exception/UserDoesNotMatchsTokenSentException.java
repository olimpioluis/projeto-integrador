package br.com.meli.projetointegrador.exception;

public class UserDoesNotMatchsTokenSentException extends RuntimeException {
    public UserDoesNotMatchsTokenSentException(String message) {
        super(message);
    }
}
