package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Payment;
import br.com.meli.projetointegrador.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class PaymentDTO {

    private long id;
    private PaymentStatus paymentStatus;
    private BigDecimal value;
    private CustomerDTO customer;
    private PurchaseCartDTO cart;

    public static PaymentDTO map(Payment payment) {
        return new PaymentDTO(payment.getId(), payment.getStatus(), payment.getValue(), CustomerDTO.map(payment.getCustomer()), PurchaseCartDTO.map(payment));
    }
}
