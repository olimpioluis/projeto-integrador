package br.com.meli.projetointegrador.service;

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
    public void save(Batch[] batches) {
        batchRepository.saveAll(Arrays.asList(batches));
    }
}
