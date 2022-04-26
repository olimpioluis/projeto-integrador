package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.InexistentWarehouseException;
import br.com.meli.projetointegrador.repository.WarehouseRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WarehouseExists implements Validator{

    private Long warehouseId;
    private WarehouseRepository warehouseRepository;

    @Override
    public void validate() {
        if (warehouseRepository.findById(warehouseId).isEmpty()) throw new InexistentWarehouseException("Warehouse " + warehouseId + " does not exists!");
    }
}
