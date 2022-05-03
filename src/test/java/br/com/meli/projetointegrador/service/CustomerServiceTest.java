package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Customer;
import br.com.meli.projetointegrador.model.User;
import br.com.meli.projetointegrador.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTest {

    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    private void initializeCustomerService() {
        MockitoAnnotations.openMocks(this);
        this.customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void findByIdTest() {

        Customer customer = new Customer(1L, new User());

        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(customer));

        assertEquals(1L, customerService.findById(1L).getId());
    }
}
