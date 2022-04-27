package br.com.meli.projetointegrador.exception.handler;

import br.com.meli.projetointegrador.dto.ErrorDTO;
import br.com.meli.projetointegrador.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(InexistentInboundOrderException.class)
    protected ResponseEntity<ErrorDTO> handleInexistentInboundOrderException(InexistentInboundOrderException ex){
        ErrorDTO error = new ErrorDTO("InexistentInboundOrderException", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InexistentBatchException.class)
    protected ResponseEntity<ErrorDTO> handleInexistentBatchExceptionException(InexistentBatchException ex){
        ErrorDTO error = new ErrorDTO("InexistentBatchException", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InexistentProductException.class)
    protected ResponseEntity<ErrorDTO> handleInexistentProductException(InexistentProductException ex){
        ErrorDTO error = new ErrorDTO("InexistentProductException", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InexistentSectionException.class)
    protected ResponseEntity<ErrorDTO> handleInexistentSectionException(InexistentSectionException ex){
        ErrorDTO error = new ErrorDTO("InexistentSectionException", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InexistentWarehouseException.class)
    protected ResponseEntity<ErrorDTO> handleInexistentWarehouseException(InexistentWarehouseException ex){
        ErrorDTO error = new ErrorDTO("InexistentWarehouseException", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SectionNotMatchWithBatchCategoryException.class)
    protected ResponseEntity<ErrorDTO> handleSectionNotMatchWithBatchCategoryException(SectionNotMatchWithBatchCategoryException ex){
        ErrorDTO error = new ErrorDTO("SectionNotMatchWithBatchCategoryException", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SectionUnavailableSpaceException.class)
    protected ResponseEntity<ErrorDTO> handleSectionUnavailableSpaceException(SectionUnavailableSpaceException ex){
        ErrorDTO error = new ErrorDTO("SectionUnavailableSpaceException", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StockManagerNotMatchWithWarehouseException.class)
    protected ResponseEntity<ErrorDTO> handleStockManagerNotMatchWithWarehouseException(StockManagerNotMatchWithWarehouseException ex){
        ErrorDTO error = new ErrorDTO("StockManagerNotMatchWithWarehouseException", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SectionNotMatchWithWarehouseException.class)
    protected ResponseEntity<ErrorDTO> handleSectionNotMatchWithWarehouseException(SectionNotMatchWithWarehouseException ex){
        ErrorDTO error = new ErrorDTO("SectionNotMatchWithWarehouseException", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
