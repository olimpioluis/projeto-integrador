package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.model.Customer;
import br.com.meli.projetointegrador.model.User;
import br.com.meli.projetointegrador.repository.CustomerRepository;
import br.com.meli.projetointegrador.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

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

        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));

        assertEquals(1L, customerService.findById(1L).getId());
    }

    @Test
    public void findByUserUsernameTest() {
        Customer customer = new Customer(1L, new User("user", "1234526845-9", "usertest@email.com", "usertest", ""));

        Mockito.when(customerRepository.findByUserUsername(Mockito.any())).thenReturn(Optional.of(customer));

        assertEquals(1L, customerService.findByUserUsername("usertest").getId());
    }
}
