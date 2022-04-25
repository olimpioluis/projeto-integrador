package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.WarehouseNotExistsException;
import br.com.meli.projetointegrador.model.Warehouse;
import br.com.meli.projetointegrador.repository.WarehouseRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WarehouseExistsValidator implements Validator{

    private Warehouse warehouse;
    private WarehouseRepository warehouseRepository;

    @Override
    public void validate() {
        if (!warehouseRepository.existsById(warehouse.getId())) throw new WarehouseNotExistsException("Este armazem não existe.");
    }
}
