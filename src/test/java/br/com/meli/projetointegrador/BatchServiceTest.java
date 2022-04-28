package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.exception.NotFoundProductException;
import br.com.meli.projetointegrador.repository.BatchRepository;
import br.com.meli.projetointegrador.service.BatchService;
import br.com.meli.projetointegrador.service.BatchServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BatchServiceTest {

    private BatchService batchService;

    @Mock
    private BatchRepository batchRepository;

    @BeforeEach
    void initConfig() {
        MockitoAnnotations.openMocks(this);
        this.batchService = new BatchServiceImpl(batchRepository);
    }

    @Test
    void EmptyBatchStockByWarehouse() {

        List<Object[]> result = new ArrayList<>();
        Mockito.when(batchRepository.groupAllByWarehouseId(Mockito.anyLong())).thenReturn(result);

        assertThrows(NotFoundProductException.class, () -> batchService.getBatchStockByWarehouse(2L));

    }

    @Test
    void NonEmptyBatchStockByWarehouse() {
        Object[] objects = new Object[0];

        List<Object[]> result = Arrays.asList(objects, objects);
        Mockito.when(batchRepository.groupAllByWarehouseId(Mockito.anyLong())).thenReturn(result);

        assertDoesNotThrow(() -> batchService.getBatchStockByWarehouse(2L));
    }
}
