package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class PurchaseCartDTO {

    private String message;
    private List<CartProductDTO> items;
    private BigDecimal totalPrice;

    public static PurchaseCartDTO map(Cart cart){
        return new PurchaseCartDTO("Purchase made successfully!", CartProductDTO.map(cart.getItems()), cart.getTotalCart());
    }
}
