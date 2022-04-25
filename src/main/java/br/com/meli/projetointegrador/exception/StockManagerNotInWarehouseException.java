package br.com.meli.projetointegrador.exception;


public class StockManagerNotInWarehouseException extends RuntimeException {

    public StockManagerNotInWarehouseException(RuntimeException exception) {
        super(exception);
    }

    public StockManagerNotInWarehouseException(String message) {
        super(message);
    }

}
