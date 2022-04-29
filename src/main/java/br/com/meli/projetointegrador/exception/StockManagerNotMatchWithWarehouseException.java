package br.com.meli.projetointegrador.exception;


public class StockManagerNotMatchWithWarehouseException extends RuntimeException {
    public StockManagerNotMatchWithWarehouseException(String message) {
        super(message);
    }

}
