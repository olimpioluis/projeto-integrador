package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentBatchException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.repository.BatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Batch> getBatchesWithExpirationDateGreaterThan3Weeks(Long productId) {
        List<Batch> batches = findAllBatchesByProduct(productId);

        return batches.stream().filter(batch -> ChronoUnit.DAYS.between(LocalDate.now(), batch.getExpirationDate()) > 21).collect(Collectors.toList());
    }

    @Override
    public List<Batch> findAllBatchesByProduct(Long productId) {
        return batchRepository.findAllByProductId(productId);
    }
}
