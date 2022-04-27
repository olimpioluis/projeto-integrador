package br.com.meli.projetointegrador.exception;

public class SectionNotMatchWithWarehouseException extends RuntimeException{

    public SectionNotMatchWithWarehouseException(RuntimeException exception) {
        super(exception);
    }

    public SectionNotMatchWithWarehouseException(String message) {
        super(message);
    }
}
