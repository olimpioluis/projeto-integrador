package br.com.meli.projetointegrador.exception;

public class InexistentWarehouseException extends RuntimeException{
        public InexistentWarehouseException(String mensagem) {
            super(mensagem);
        }
}
