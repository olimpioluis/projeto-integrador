package br.com.meli.projetointegrador;


import br.com.meli.projetointegrador.exception.InexistentWarehouseException;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.model.StockManager;
import br.com.meli.projetointegrador.model.Warehouse;
import br.com.meli.projetointegrador.repository.WarehouseRepository;
import br.com.meli.projetointegrador.service.WarehouseService;
import br.com.meli.projetointegrador.service.WarehouseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WarehouseServiceTest {


    WarehouseService warehouseService;

    @Mock
    WarehouseRepository warehouseRepository;

    @BeforeEach
    private void initializeStockManegerService() {
        MockitoAnnotations.openMocks(this);
        this.warehouseService = new WarehouseServiceImpl(warehouseRepository);
    }

    @Test
    public void findByIdTest(){
        Warehouse warehouse = new Warehouse(1L,"Warehouse 1", Collections.singletonList(new StockManager()), Collections.singletonList(new Section()));
        Mockito.when(warehouseRepository.findById(Mockito.any())).thenReturn(Optional.of(warehouse));

        assertEquals(warehouse.getName(), warehouseService.findById(1L).getName());
    }

    @Test
    public void inexistentWarehouseException(){
        Mockito.when(warehouseRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(InexistentWarehouseException.class, () -> warehouseService.findById(1L));
    }
}
