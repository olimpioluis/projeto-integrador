package br.com.meli.projetointegrador.exception;

public class SectionUnavailableSpaceException extends  RuntimeException{
    public SectionUnavailableSpaceException(String mensagem) {
        super(mensagem);
    }
}
