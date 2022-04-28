package br.com.meli.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TotalCartPriceDTO {
    private BigDecimal totalPrice;

    public static TotalCartPriceDTO map(BigDecimal totalCartPrice) {
        return new TotalCartPriceDTO(totalCartPrice);
    }
}
