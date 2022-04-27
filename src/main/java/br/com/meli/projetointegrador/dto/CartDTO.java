package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Cart;
import br.com.meli.projetointegrador.model.Customer;
import br.com.meli.projetointegrador.model.Item;
import br.com.meli.projetointegrador.model.OrderStatus;
import br.com.meli.projetointegrador.service.AdvertisementService;
import br.com.meli.projetointegrador.service.CustomerService;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartDTO {

    private LocalDate orderDate;
    private Long customerId;
    private OrderStatus orderStatus;
    private List<ItemDTO> items;

    public static Cart map(CartDTO cartDTO, CustomerService customerService, AdvertisementService advertisementService) {
        Customer customer = customerService.findById(cartDTO.getCustomerId());
        List<Item> items = cartDTO.getItems().stream().map(item -> ItemDTO.map(item, advertisementService)).collect(Collectors.toList());

        return Cart.builder()
                .customer(customer)
                .orderStatus(cartDTO.getOrderStatus())
                .items(items)
                .build();
    }
}
