package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentBatchException;
import br.com.meli.projetointegrador.exception.NotFoundProductException;
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

    @Override
    public Batch findById(Long id) {
        return batchRepository.findById(id).orElseThrow(() -> new InexistentBatchException("Batch " + id + " does not exists!"));
    }

    @Override
    public List<Object[]> getBatchStockByWarehouse(Long productId) {
        List<Object[]> result = batchRepository.groupAllByWarehouseId(productId);

        if (result.size() == 0) {
            throw new NotFoundProductException("The Product ID: " + productId + " does not exists!");
        }
        return result;
    }
}
