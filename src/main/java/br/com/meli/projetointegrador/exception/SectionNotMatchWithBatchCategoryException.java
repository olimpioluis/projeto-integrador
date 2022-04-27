package br.com.meli.projetointegrador.exception;

public class SectionNotMatchWithBatchCategoryException extends RuntimeException{
    public SectionNotMatchWithBatchCategoryException(RuntimeException exception) {
        super(exception);
    }

    public SectionNotMatchWithBatchCategoryException(String message) {
        super(message);
    }
}
