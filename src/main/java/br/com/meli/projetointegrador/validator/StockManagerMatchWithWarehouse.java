package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.StockManagerNotMatchWithWarehouseException;
import br.com.meli.projetointegrador.model.StockManager;
import br.com.meli.projetointegrador.repository.StockManagerRepository;
import br.com.meli.projetointegrador.service.StockManagerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StockManagerMatchWithWarehouse implements Validator {

    private final StockManagerService stockManagerService;
    private final Long stockManagerId;
    private final Long warehouseId;

    @Override
    public void validate() {
        StockManager stockManager = stockManagerService.findById(stockManagerId);
        if (stockManager.getWarehouse() != null && !stockManager.getWarehouse().getId().equals(warehouseId)) {
            throw new StockManagerNotMatchWithWarehouseException("Stock Manager does not belong to this warehouse!");
        }
    }
}
