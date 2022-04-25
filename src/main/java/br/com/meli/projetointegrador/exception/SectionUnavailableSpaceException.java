package br.com.meli.projetointegrador.exception;

public class SectionUnavailableSpaceException extends  RuntimeException{

    public SectionUnavailableSpaceException(RuntimeException e) {
        super(e);
    }
    public SectionUnavailableSpaceException(String mensagem) {
        super(mensagem);
    }
}
