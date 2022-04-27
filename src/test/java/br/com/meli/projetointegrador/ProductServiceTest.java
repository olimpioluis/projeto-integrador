package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.model.*;
import br.com.meli.projetointegrador.repository.ProductRepository;
import br.com.meli.projetointegrador.service.ProductService;
import br.com.meli.projetointegrador.service.ProductServiceImpl;
import br.com.meli.projetointegrador.service.SectionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    private void initializeProductService() {
        MockitoAnnotations.openMocks(this);
        this.productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(productRepository.findById(1L))
                .thenReturn(java.util.Optional.of(new Product(1L, "Product 1", 20.0, 2.0, 2.0, Arrays.asList(new Batch()))));
        Product product = productRepository.findById(1L).orElse(new Product());

        assertEquals("Product 1", product.getName());
    }
}
