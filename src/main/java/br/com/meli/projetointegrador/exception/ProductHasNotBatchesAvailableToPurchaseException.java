package br.com.meli.projetointegrador.exception;

public class ProductHasNotBatchesAvailableToPurchaseException extends RuntimeException {
    public ProductHasNotBatchesAvailableToPurchaseException(String message) {
        super(message);
    }
}
