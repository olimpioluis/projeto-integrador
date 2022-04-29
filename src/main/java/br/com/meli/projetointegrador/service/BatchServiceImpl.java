package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentBatchException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.repository.BatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class BatchServiceImpl implements BatchService {

    private BatchRepository batchRepository;

    @Override
    public List<Batch> save(List<Batch> batches) {
        return batchRepository.saveAll(batches);
    }

    @Override
    public Batch findById(Long id) {
        return batchRepository.findById(id).orElseThrow(() -> new InexistentBatchException("Batch " + id + " does not exists!"));
    }
}
