package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentProductException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Product;
import br.com.meli.projetointegrador.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private BatchService batchService;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new InexistentProductException("Product " + id + " does not exists!"));
    }

    @Override
    public Integer getTotalQuantity(Long id) {
        Product product = findById(id);
        List<Batch> batches = batchService.getBatchesWithExpirationDateGreaterThan3Weeks(id);

        return batches.stream().reduce(0, (acc, nextBatch) -> acc + nextBatch.getCurrentQuantity(), Integer::sum);
    }
}
