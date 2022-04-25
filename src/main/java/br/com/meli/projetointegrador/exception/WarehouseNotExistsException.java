package br.com.meli.projetointegrador.exception;

public class WarehouseNotExistsException extends  RuntimeException{

        private static final long serialVersionUID = 1L;
        public WarehouseNotExistsException(RuntimeException e) {
            super(e);
        }
        public WarehouseNotExistsException(String mensagem) {
            super(mensagem);
        }
}
