package br.com.meli.projetointegrador.exception;

public class SectionMatchWithBatchCategoryException extends RuntimeException{
    public SectionMatchWithBatchCategoryException(RuntimeException exception) {
        super(exception);
    }

    public SectionMatchWithBatchCategoryException(String message) {
        super(message);
    }
}
