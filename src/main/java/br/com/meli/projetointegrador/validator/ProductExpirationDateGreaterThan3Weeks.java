package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.ProductHasNotBatchesAvailableToPurchaseException;
import br.com.meli.projetointegrador.model.Item;
import br.com.meli.projetointegrador.service.BatchService;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProductExpirationDateGreaterThan3Weeks implements Validator {

    private List<Item> items;
    private BatchService batchService;

    @Override
    public void validate() {
        List<String> productsWithoutStock = new ArrayList<>();

        items.forEach(item -> {
            if (batchService.getBatchesWithExpirationDateGreaterThan3Weeks(item.getAdvertisement().getProduct().getId()).isEmpty()) {
                productsWithoutStock.add(item.getAdvertisement().getProduct().getName());
            }
        });

        if (productsWithoutStock.size() > 0) throw new ProductHasNotBatchesAvailableToPurchaseException(productsWithoutStock.toString().replaceAll("[\\[\\]]", ""));
    }
}
