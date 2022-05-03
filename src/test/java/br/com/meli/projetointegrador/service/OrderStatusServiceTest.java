package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.*;
import br.com.meli.projetointegrador.repository.ItemRepository;
import br.com.meli.projetointegrador.repository.OrderStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderStatusServiceTest {
    @Mock
    private OrderStatusRepository orderStatusRepository;

    private OrderStatusService orderStatusService;

    @BeforeEach
    private void initializeOrderStatusService() {
        MockitoAnnotations.openMocks(this);
        this.orderStatusService = new OrderStatusServiceImpl(orderStatusRepository);
    }


    @Test
    public void saveTest() {
        OrderStatus orderStatus = new OrderStatus(1L, StatusCode.CART);
        Mockito.when(orderStatusRepository.save(Mockito.any())).thenReturn(orderStatus);

        assertEquals(orderStatus.getStatusCode(), orderStatusService.save(orderStatus).getStatusCode());
    }
}
