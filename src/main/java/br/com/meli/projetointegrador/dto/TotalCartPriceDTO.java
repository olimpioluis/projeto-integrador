package br.com.meli.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
/**
 * Classe DTO para retornar preco total de uma lista de produtos.
 * @author Igor de Souza Nogueira
 * @author Luis Felipe Floriano Olimpio
 */
@Getter
@AllArgsConstructor
public class TotalCartPriceDTO {
    private BigDecimal totalPrice;

    public static TotalCartPriceDTO map(BigDecimal totalCartPrice) {
        return new TotalCartPriceDTO(totalCartPrice);
    }
}
