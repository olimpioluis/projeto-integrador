package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.StockManagerNotInWarehouseException;
import br.com.meli.projetointegrador.model.StockManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StockManagerNotInWarehouse implements Validator {

    private final StockManagerRepository stockManagerRepository;
    private final String stockManagerCode;
    private final String warehouseCode;

    @Override
    public void valida() {
        StockManager stockManager = stockManagerRepository.findById(stockManagerCode);
        if (stockManager.getWarehouse() != null && !stockManager.getWarehouse().getId().equals(warehouseCode)) {
            throw new StockManagerNotInWarehouseException("Stock Manager does not belong to this warehouse!");
        }
    }
}
