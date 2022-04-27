package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Batch;

public interface BatchService {
    void save(Batch[] batches);
    Batch findById(Long id);
}
