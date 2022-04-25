package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.StockManagerNotInWarehouseException;
import br.com.meli.projetointegrador.model.StockManager;
import br.com.meli.projetointegrador.repository.StockManagerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StockManagerNotInWarehouse implements Validator {

    private final StockManagerRepository stockManagerRepository;
    private final Long stockManagerId;
    private final Long warehouseId;

    @Override
    public void validate() {
        StockManager stockManager = stockManagerRepository.findById(stockManagerId).orElse(new StockManager());
        if (stockManager.getWarehouse() != null && !stockManager.getWarehouse().getId().equals(warehouseId)) {
            throw new StockManagerNotInWarehouseException("Stock Manager does not belong to this warehouse!");
        }
    }
}
