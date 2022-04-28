package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Cart;


import br.com.meli.projetointegrador.model.StatusCode;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class CartWithStatusDTO {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate orderDate;
    private StatusCode statusCode;
    private List<CartProductDTO> items;

    public static CartWithStatusDTO map(Cart cart){
        return new CartWithStatusDTO(cart.getOrderDate(), cart.getOrderStatus().getStatusCode(), CartProductDTO.map(cart.getItems()));
    }

}
