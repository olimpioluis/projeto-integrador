package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.dto.ProductDTOi;
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

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<ProductDTOi> findAllByBatchListExists() {
        return productRepository.findAllByBatchListExists();
    }

    public List<ProductDTOi> findAllByBatchListExistsBySection(String category) {
        return productRepository.findAllByBatchListExistsBySection(category);
    }

    @Override
    public Integer getTotalQuantity(Long id) {
        List<Batch> batches = batchService.getBatchesWithExpirationDateGreaterThan3Weeks(id);
        return batches.stream().reduce(0, (acc, nextBatch) -> acc + nextBatch.getCurrentQuantity(), Integer::sum);
    }
}
