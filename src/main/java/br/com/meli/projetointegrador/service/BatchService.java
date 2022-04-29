package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Batch;

import java.util.List;

public interface BatchService {
    List<Batch> save(List<Batch> batches);
    Batch findById(Long id);
}
