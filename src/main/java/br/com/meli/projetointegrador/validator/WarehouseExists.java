package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.service.WarehouseService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WarehouseExists implements Validator{

    private Long warehouseId;
    private WarehouseService warehouseService;

    @Override
    public void validate() {
        warehouseService.findById(warehouseId);
    }
}
