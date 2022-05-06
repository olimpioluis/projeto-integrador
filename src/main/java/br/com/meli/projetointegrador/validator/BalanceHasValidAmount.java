package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.InsufficientBalanceException;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class BalanceHasValidAmount implements Validator {

    private BigDecimal futureBalance;
    private String message;

    @Override
    public void validate() {
        if (futureBalance.compareTo(BigDecimal.ZERO) < 0) throw new InsufficientBalanceException(message);
    }
}
