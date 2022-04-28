package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Batch;

import java.util.List;

public interface BatchService {
    void save(Batch[] batches);
    Batch findById(Long id);
    List<Object[]> getBatchStockByWarehouse(Long id);
}
