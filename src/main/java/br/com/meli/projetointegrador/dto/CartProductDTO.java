package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CartProductDTO {

    private String name;
    private Integer quantity;

    public static CartProductDTO map(Item item) {
        return new CartProductDTO(item.getAdvertisement().getProduct().getName(), item.getQuantity());
    }

    public static List<CartProductDTO> map(List<Item> items) {
        return items.stream().map(CartProductDTO::map).collect(Collectors.toList());
    }
}
