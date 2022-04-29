package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentBatchException;
import br.com.meli.projetointegrador.exception.InexistentInboundOrderException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.InboundOrder;
import br.com.meli.projetointegrador.model.Product;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.repository.BatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BatchServiceTest {
    private BatchServiceImpl batchService;

    @Mock
    private BatchRepository batchRepository;

    @BeforeEach
    private void initializeBatchService() {
        MockitoAnnotations.openMocks(this);
        this.batchService = new BatchServiceImpl(batchRepository);
    }

    private List<Batch> generateBatches(){
        return Arrays.asList(
                new Batch(1L, 15.0, 12.0, 200, 200,
                        LocalDate.of(2022, 1, 1), LocalDateTime.of(2022, 1, 1, 0, 0),
                        LocalDate.of(2022, 2, 2), new Product(), new InboundOrder(), new Section()),
                new Batch(2L, 0.0, -15.0, 50, 50,
                        LocalDate.of(2022, 3, 3), LocalDateTime.of(2022, 1, 3, 0, 0),
                        LocalDate.of(2022, 4, 4), new Product(), new InboundOrder(), new Section()));
    }

    @Test
    public void saveTest() {
        List<Batch> batches = generateBatches();

        Mockito.when(batchRepository.saveAll(Mockito.any())).thenReturn(batches);

        assertEquals(batches.get(0), batchService.save(batches).get(0));
    }

    @Test
    public void findByIdTest() {
        List<Batch> batches = generateBatches();

        Mockito.when(batchRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(batches.get(0)));

        assertEquals(batches.get(0), batchService.findById(1L));
    }

    @Test
    public void inexistentBatchExceptionTest(){
        Mockito.when(batchRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(InexistentBatchException.class, () -> batchService.findById(1L));
    }


}
