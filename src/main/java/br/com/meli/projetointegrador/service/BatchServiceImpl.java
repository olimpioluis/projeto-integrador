package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentBatchException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.repository.BatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class BatchServiceImpl implements BatchService {

    private BatchRepository batchRepository;

    @Override
    public void save(Batch[] batches) {
        batchRepository.saveAll(Arrays.asList(batches));
    }

    @Override
    public Batch findById(Long id) {
        return batchRepository.findById(id).orElseThrow(() -> new InexistentBatchException("Batch " + id + " does not exists!"));
    }
}
