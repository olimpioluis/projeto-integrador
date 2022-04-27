package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentProductException;
import br.com.meli.projetointegrador.model.Product;
import br.com.meli.projetointegrador.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new InexistentProductException("Product " + id + " does not exists!"));
    }
}
