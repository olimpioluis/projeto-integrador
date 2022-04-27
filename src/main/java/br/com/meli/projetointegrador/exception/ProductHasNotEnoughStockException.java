package br.com.meli.projetointegrador.exception;

public class ProductHasNotEnoughStockException extends RuntimeException {
    public ProductHasNotEnoughStockException(RuntimeException exception) {
        super(exception);
    }

    public ProductHasNotEnoughStockException(String message) {
        super(message);
    }
}
