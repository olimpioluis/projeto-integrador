package br.com.meli.projetointegrador.exception;

public class InexistentWarehouseException extends RuntimeException{

        public InexistentWarehouseException(RuntimeException e) {
            super(e);
        }
        public InexistentWarehouseException(String mensagem) {
            super(mensagem);
        }
}
