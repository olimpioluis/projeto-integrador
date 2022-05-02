package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentProductException;
import br.com.meli.projetointegrador.model.*;
import br.com.meli.projetointegrador.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Mockito.when(productRepository.findById(Mockito.any()))
                .thenReturn(java.util.Optional.of(new Product(1L, "Product 1", 20.0, 2.0, 2.0, Collections.singletonList(new Batch()))));

        assertEquals("Product 1", productService.findById(1L).getName());
    }

    @Test
    public void inexistentProductException(){
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(InexistentProductException.class, () -> productService.findById(1L));
    }
}
