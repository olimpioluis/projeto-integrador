package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentWarehouseException;
import br.com.meli.projetointegrador.model.Warehouse;
import br.com.meli.projetointegrador.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private WarehouseRepository warehouseRepository;

    @Override
    public Warehouse findById(Long id) {
        return warehouseRepository.findById(id).orElseThrow(() -> new InexistentWarehouseException("The informed Warehouse does not exists!"));
    }
}
