package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.*;
import br.com.meli.projetointegrador.model.*;
import br.com.meli.projetointegrador.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InboundOrderServiceTest {

    private InboundOrderServiceImpl inboundOrderService;

    @Mock
    private InboundOrderRepository inboundOrderRepository;
    @Mock
    private BatchService batchService;
    @Mock
    private SectionService sectionService;
    @Mock
    private StockManagerService stockManagerService;
    @Mock
    private WarehouseService warehouseService;

    @BeforeEach
    private void initializeInboundOrderService(){
       MockitoAnnotations.openMocks(this);
       this.inboundOrderService = new InboundOrderServiceImpl(inboundOrderRepository, batchService, sectionService, stockManagerService, warehouseService);
   }

    private InboundOrder generateInboundOrder(){
        StockManager stockManagerIgor = new StockManager(1L, new Person(1L, "Igor", "123.456.789-10", "Igor@gmail.com", 'M'), new Warehouse());
        StockManager stockManagerJederson = new StockManager(2L, new Person(2L, "Jederson", "103.476.729-30", "jederson@gmail.com", 'M'),new Warehouse());

        Warehouse warehouse = new Warehouse(1L,"Warehouse 1", Arrays.asList(stockManagerIgor, stockManagerJederson), Collections.singletonList(new Section()));

        stockManagerIgor.setWarehouse(warehouse);
        stockManagerJederson.setWarehouse(warehouse);

        Section section1 = new Section(1L, "Section 1", Category.FRESH, 6, 6, warehouse, Collections.singletonList(new Batch()));
        Section section2 = new Section(2L, "Section 2", Category.FROZEN, 6, 6, warehouse, Collections.singletonList(new Batch()));

        warehouse.setSectionList(Arrays.asList(section1, section2));

        Product productTomato= new Product(1L, "Tomato", 2.00, 5.0, 4.0, Collections.singletonList(new Batch()));
        Product productMeat= new Product(2L, "Meat", 40.00, 10.0, 10.0, Collections.singletonList(new Batch()));

        List<Batch> batchList = Arrays.asList(
                new Batch(1L, 15.0, 12.0, 200, 200,
                        LocalDate.of(2022, 1, 1), LocalDateTime.of(2022, 1, 1, 0, 0),
                        LocalDate.of(2022, 2, 2), productTomato, new InboundOrder(), section1),
                new Batch(2L, 0.0, -15.0, 50, 50,
                    LocalDate.of(2022, 3, 3), LocalDateTime.of(2022, 1, 3, 0, 0),
                    LocalDate.of(2022, 4, 4), productMeat, new InboundOrder(), section2));

        productTomato.setBatchList(Collections.singletonList(batchList.get(0)));
        productMeat.setBatchList(Collections.singletonList(batchList.get(1)));

        section1.setBatchList(Collections.singletonList(batchList.get(0)));
        section2.setBatchList(Collections.singletonList(batchList.get(1)));

        return new InboundOrder(1L, 1, LocalDate.now(), section1, Collections.singletonList(batchList.get(0)), stockManagerIgor);
    }

    @Test
    public void saveInboundOrderTest(){
        InboundOrder inboundOrder = generateInboundOrder();

        Mockito.when(inboundOrderRepository.save(Mockito.any())).thenReturn(inboundOrder);
        Mockito.when(batchService.save(Mockito.any())).thenReturn(inboundOrder.getBatchList());
        Mockito.when(sectionService.findById(Mockito.any())).thenReturn(inboundOrder.getSection());
        Mockito.when(warehouseService.findById(Mockito.any())).thenReturn(inboundOrder.getSection().getWarehouse());

        List<Batch> response = inboundOrderService.save(inboundOrder);

        assertEquals(generateInboundOrder().getBatchList().get(0).getId(), response.get(0).getId());
    }

    @Test
    public void updateInboundOrderTest(){
        InboundOrder inboundOrder = generateInboundOrder();

        Mockito.when(inboundOrderRepository.save(Mockito.any())).thenReturn(inboundOrder);
        Mockito.when(batchService.save(Mockito.any())).thenReturn(inboundOrder.getBatchList());
        Mockito.when(sectionService.findById(Mockito.any())).thenReturn(inboundOrder.getSection());


        inboundOrder.getBatchList().get(0).setExpirationDate(LocalDate.of(2022, 6, 9));

        List<Batch> response = inboundOrderService.update(inboundOrder);

        assertEquals(LocalDate.of(2022, 6, 9), response.get(0).getExpirationDate());
    }

    @Test
    public void InboudOrderFindByIdTest(){
        InboundOrder inboundOrder = generateInboundOrder();

        Mockito.when(inboundOrderRepository.findById(Mockito.any())).thenReturn(Optional.of(inboundOrder));

        InboundOrder response = inboundOrderService.findById(generateInboundOrder().getId());

        assertEquals(inboundOrder.getOrderNumber(), response.getOrderNumber());

    }

    @Test
    public void SectionAvailableSpaceValidatorTest(){
        InboundOrder inboundOrder = generateInboundOrder();
        inboundOrder.getSection().setCurrentSize(0);

        Mockito.when(inboundOrderRepository.save(Mockito.any())).thenReturn(inboundOrder);
        Mockito.when(sectionService.findById(Mockito.any())).thenReturn(inboundOrder.getSection());
        Mockito.when(warehouseService.findById(Mockito.any())).thenReturn(inboundOrder.getSection().getWarehouse());

        assertThrows(SectionUnavailableSpaceException.class, () -> inboundOrderService.save(inboundOrder));

    }

    @Test
    public void WarehouseExistsValidatorTest(){
        InboundOrder inboundOrder = generateInboundOrder();

        Mockito.when(warehouseService.findById(Mockito.any())).thenThrow(InexistentWarehouseException.class);

        assertThrows(InexistentWarehouseException.class, () -> inboundOrderService.save(inboundOrder));

    }

    @Test
    public void SectionExistsValidatorTest(){
        InboundOrder inboundOrder = generateInboundOrder();

        Mockito.when(sectionService.findById(Mockito.any())).thenThrow(InexistentSectionException.class);

        assertThrows(InexistentSectionException.class, () -> inboundOrderService.save(inboundOrder));
    }

    @Test
    public void SectionMatchWithWarehouseValidatorTest(){
        InboundOrder inboundOrder = generateInboundOrder();

        Section section3 = new Section(3L, "Section 3", Category.FRESH, 6, 6, new Warehouse(), Collections.singletonList(new Batch()));

        Mockito.when(inboundOrderRepository.save(Mockito.any())).thenReturn(inboundOrder);
        Mockito.when(sectionService.findById(Mockito.any())).thenReturn(section3);
        Mockito.when(warehouseService.findById(Mockito.any())).thenReturn(inboundOrder.getSection().getWarehouse());

        assertThrows(SectionNotMatchWithWarehouseException.class, () -> inboundOrderService.save(inboundOrder));
    }

    @Test
    public void SectionMatchWithBatchCategoryValidatorTest(){
        InboundOrder inboundOrder = generateInboundOrder();

        inboundOrder.getSection().setCategory(Category.REFRIGERATED);

        Mockito.when(inboundOrderRepository.save(Mockito.any())).thenReturn(inboundOrder);
        Mockito.when(sectionService.findById(Mockito.any())).thenReturn(inboundOrder.getSection());
        Mockito.when(warehouseService.findById(Mockito.any())).thenReturn(inboundOrder.getSection().getWarehouse());

        assertThrows(SectionNotMatchWithBatchCategoryException.class, () -> inboundOrderService.save(inboundOrder));
    }

    @Test
    public void inexistentInboundOrderException(){
        Mockito.when(inboundOrderRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(InexistentInboundOrderException.class, () -> inboundOrderService.findById(1L));
    }
}
