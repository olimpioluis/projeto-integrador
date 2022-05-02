package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentStockManagerException;
import br.com.meli.projetointegrador.model.StockManager;
import br.com.meli.projetointegrador.model.User;
import br.com.meli.projetointegrador.model.Warehouse;
import br.com.meli.projetointegrador.repository.StockManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockManagerServiceTest {

    StockManagerService stockManagerService;

    @Mock
    StockManagerRepository stockManagerRepository;

    @BeforeEach
    private void initializeStockManegerService() {
        MockitoAnnotations.openMocks(this);
        this.stockManagerService = new StockManagerServiceImpl(stockManagerRepository);
    }

    @Test
    public void findByIdTest(){
        StockManager stockManager = new StockManager(1L, new User(1L, "Igor", "123.456.789-10", "igor@gmail.com"), new Warehouse());
        Mockito.when(stockManagerRepository.findById(Mockito.any())).thenReturn(Optional.of(stockManager));

        assertEquals(stockManager.getUser().getName(), stockManagerService.findById(1L).getUser().getName());

    }

    @Test
    public void inexistentStockManagerExceptionTest(){
        Mockito.when(stockManagerRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(InexistentStockManagerException.class, () -> stockManagerService.findById(1L));
    }




}
