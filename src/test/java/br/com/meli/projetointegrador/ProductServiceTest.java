package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.dto.ProductByBatchResponse;
import br.com.meli.projetointegrador.dto.ProductByBatchResponseImpl;
import br.com.meli.projetointegrador.model.*;
import br.com.meli.projetointegrador.repository.BatchRepository;
import br.com.meli.projetointegrador.repository.ProductRepository;
import br.com.meli.projetointegrador.service.ProductService;
import br.com.meli.projetointegrador.service.ProductServiceImpl;
import br.com.meli.projetointegrador.service.SectionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BatchRepository batchRepository;

    @BeforeEach
    private void initializeProductService() {
        MockitoAnnotations.openMocks(this);
        this.productService = new ProductServiceImpl(productRepository, batchRepository);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(productRepository.findById(1L))
                .thenReturn(java.util.Optional.of(new Product(1L, "Product 1", 20.0, 2.0, 2.0, Arrays.asList(new Batch()))));
        Product product = productRepository.findById(1L).orElse(new Product());

        assertEquals("Product 1", product.getName());
    }

    @Test
    void getProductsThatHaveBatch() {
        List<ProductByBatchResponse> listProductBatch = Collections.singletonList(
                new ProductByBatchResponseImpl(BigInteger.ONE, 6.0,"Shampoo", 17.5, 5.0,
                        BigInteger.TWO, "2022-02-02", 20)
        );
        List<ProductByBatchResponse> listProductQuantity = Collections.singletonList(
                new ProductByBatchResponseImpl(BigInteger.ONE, 17.5, "Shampoo",5.0, 6.0, BigInteger.valueOf(3),
                        "2023-02-02", 50)
        );
        List<ProductByBatchResponse> listProductExpiration = Collections.singletonList(
                new ProductByBatchResponseImpl(BigInteger.ONE,17.5,"Shampoo" ,5.0, 6.0, BigInteger.valueOf(4),
                        "2024-08-08", 30)
        );

        Mockito.when(batchRepository.getAllProductThatHaveBatch(Mockito.anyLong())).thenReturn(listProductBatch);
        Mockito.when(batchRepository.getAllProductThatHaveBatchQuantity(Mockito.anyLong())).thenReturn(listProductQuantity);
        Mockito.when(batchRepository.getAllProductThatHaveBatchExpiration(Mockito.anyLong())).thenReturn(listProductExpiration);

        assertAll(
                () -> assertEquals(BigInteger.TWO, productService.getAllProductThatHaveBatch(1L, "L").get(0).getBatchId()),
                () -> assertEquals(50, productService.getAllProductThatHaveBatch(1L, "C").get(0).getCurrentQuantity()),
                () -> assertEquals("2024-08-08",
                        productService.getAllProductThatHaveBatch(1L, "F").get(0).getExpirationDate()),
                () -> assertTrue(productService.getAllProductThatHaveBatch(1L, "XYZW").isEmpty())
        );

    }
}
