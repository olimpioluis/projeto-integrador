package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.dto.ProductByBatchResponse;
import br.com.meli.projetointegrador.exception.InexistentProductException;
import br.com.meli.projetointegrador.model.Product;
import br.com.meli.projetointegrador.repository.BatchRepository;
import br.com.meli.projetointegrador.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private BatchRepository batchRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new InexistentProductException("Product " + id + " does not exists!"));
    }

    @Override
    public List<ProductByBatchResponse> getAllProductThatHaveBatch(Long id, String orderBy) {

        List<ProductByBatchResponse> productByBatchResponseList = new ArrayList<>();

        switch (orderBy) {
            case "L":
                return batchRepository.getAllProductThatHaveBatch(id, "batchId");
            case "C":
                batchRepository.getAllProductThatHaveBatch(id, "currentQuantity");
            case "F":
                return batchRepository.getAllProductThatHaveBatch(id, "expirationDate");
            default:
                // throw exception
                return productByBatchResponseList;
        }

    }
}
