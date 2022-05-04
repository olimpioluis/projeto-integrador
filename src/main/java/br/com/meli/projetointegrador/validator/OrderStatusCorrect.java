package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.StatusCodeIncorrectException;
import br.com.meli.projetointegrador.model.StatusCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderStatusCorrect implements Validator {

    private StatusCode statusCode;

    @Override
    public void validate() {
        if (!statusCode.equals(StatusCode.CART)) throw new StatusCodeIncorrectException("Status code " + statusCode + " not correct, expected " + StatusCode.CART.name());
    }
}
