package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.*;
import br.com.meli.projetointegrador.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private BatchService batchService;

    @BeforeEach
    private void initializeProductService() {
        MockitoAnnotations.openMocks(this);
        this.productService = new ProductServiceImpl(productRepository, batchService);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(productRepository.findById(1L))
                .thenReturn(java.util.Optional.of(new Product(1L, "Product 1", 20.0, 2.0, 2.0, Arrays.asList(new Batch()))));
        Product product = productRepository.findById(1L).orElse(new Product());

        assertEquals("Product 1", product.getName());
    }
}
