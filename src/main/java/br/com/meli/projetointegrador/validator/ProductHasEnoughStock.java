package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.ProductHasNotEnoughStockException;
import br.com.meli.projetointegrador.model.Item;
import br.com.meli.projetointegrador.service.ProductService;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProductHasEnoughStock implements Validator {

    private ProductService productService;
    private List<Item> items;

    @Override
    public void validate() {
        List<String> productsWithoutStock = new ArrayList<>();

        items.forEach(item -> {
            if (productService.getTotalQuantity(item.getAdvertisement().getProduct().getId()) < item.getQuantity()) {
                productsWithoutStock.add(item.getAdvertisement().getProduct().getName());
            }
        });

        if (productsWithoutStock.size() > 0) throw new ProductHasNotEnoughStockException(productsWithoutStock.toString().replaceAll("[\\[\\]]", ""));
    }
}
