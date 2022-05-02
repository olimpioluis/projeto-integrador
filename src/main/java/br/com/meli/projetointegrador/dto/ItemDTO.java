package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Advertisement;
import br.com.meli.projetointegrador.model.Item;
import br.com.meli.projetointegrador.service.AdvertisementService;
import lombok.Getter;

@Getter
public class ItemDTO {

    private Long advertisementId;
    private Integer quantity;

    public static Item map(ItemDTO itemDTO, AdvertisementService advertisementService) {
        Advertisement advertisement = advertisementService.findById(itemDTO.advertisementId);

        return Item.builder()
                .advertisement(advertisement)
                .quantity(itemDTO.getQuantity())
                .build();
    }
}
