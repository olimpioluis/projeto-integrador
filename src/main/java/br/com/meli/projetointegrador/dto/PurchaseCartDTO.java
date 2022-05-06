package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Cart;
import br.com.meli.projetointegrador.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class PurchaseCartDTO {

    private BigDecimal totalPrice;
    private Long paymentId;
    private List<CartProductDTO> items;

    public static PurchaseCartDTO map(Payment payment){
        return new PurchaseCartDTO(payment.getCart().getTotalCart(), payment.getId(), CartProductDTO.map(payment.getCart().getItems()));
    }
}
