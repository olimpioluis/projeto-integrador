package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentStockManagerException;
import br.com.meli.projetointegrador.model.StockManager;
import br.com.meli.projetointegrador.repository.StockManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StockManagerServiceImpl implements StockManagerService{

    StockManagerRepository stockManagerRepository;

    @Override
    public StockManager findById(Long id) {
        return stockManagerRepository.findById(id).orElseThrow(() -> new InexistentStockManagerException("Stock Manager "+ id + " does not exists!"));
    }

}
