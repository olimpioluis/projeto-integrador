package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.model.*;
import br.com.meli.projetointegrador.repository.WalletRepository;
import br.com.meli.projetointegrador.security.services.UserDetailsImpl;
import br.com.meli.projetointegrador.service.CustomerService;
import br.com.meli.projetointegrador.service.WalletService;
import br.com.meli.projetointegrador.service.WalletServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalletServiceTest {

    private WalletService walletService;

    @Mock
    private WalletRepository walletRepository;
    @Mock
    private CustomerService customerService;

    @BeforeEach
    private void initializePaymentService() {
        MockitoAnnotations.openMocks(this);
        this.walletService = new WalletServiceImpl(walletRepository, customerService);
    }

    private void initializeAuthentication() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when((UserDetailsImpl) authentication.getPrincipal()).thenReturn(UserDetailsImpl.build(new User(1L, "Igor", "123.456.789-10", "igor@gmail.com", "igor_sn", "abcd1234", Set.of(new Role(1, ERole.ROLE_CUSTOMER)))));
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void saveTest() {
        initializeAuthentication();

        Customer customer = new Customer(1L, new User("user", "1234526845-9", "usertest@email.com", "usertest", ""));
        Wallet wallet = new Wallet(1L, "1234-5", BigDecimal.valueOf(300), customer);

        Mockito.when(customerService.findByUserUsername(Mockito.any())).thenReturn(customer);
        Mockito.when(walletRepository.save(Mockito.any())).thenReturn(wallet);

        assertEquals(1L, walletService.save(wallet).getId());
    }

    @Test
    public void findByIdTest() {
        Wallet wallet = new Wallet(1L, "1234-5", BigDecimal.valueOf(300), new Customer(1L, new User("user", "1234526845-9", "usertest@email.com", "usertest", "")));

        Mockito.when(walletRepository.findById(Mockito.any())).thenReturn(Optional.of(wallet));

        assertEquals(1L, walletService.findById(1L).getId());
    }

    @Test
    public void depositTest() {
        initializeAuthentication();

        Customer customer = new Customer(1L, new User("user", "1234526845-9", "usertest@email.com", "usertest", ""));
        Wallet wallet = new Wallet(1L, "1234-5", BigDecimal.valueOf(300), customer);

        Mockito.when(customerService.findByUserUsername(Mockito.any())).thenReturn(customer);
        Mockito.when(walletRepository.save(Mockito.any())).thenReturn(wallet);
        Mockito.when(walletRepository.findById(Mockito.any())).thenReturn(Optional.of(wallet));

        assertEquals(BigDecimal.valueOf(500), walletService.deposit(1L, BigDecimal.valueOf(200)).getBalance());
    }

    @Test
    public void withdrawTest() {
        initializeAuthentication();

        Customer customer = new Customer(1L, new User("user", "1234526845-9", "usertest@email.com", "usertest", ""));
        Wallet wallet = new Wallet(1L, "1234-5", BigDecimal.valueOf(300), customer);

        Mockito.when(customerService.findByUserUsername(Mockito.any())).thenReturn(customer);
        Mockito.when(walletRepository.save(Mockito.any())).thenReturn(wallet);
        Mockito.when(walletRepository.findById(Mockito.any())).thenReturn(Optional.of(wallet));

        assertEquals(BigDecimal.valueOf(100), walletService.withdraw(1L, BigDecimal.valueOf(200)).getBalance());
    }

    @Test
    public void getWalletTest() {
        initializeAuthentication();

        Customer customer = new Customer(1L, new User("user", "1234526845-9", "usertest@email.com", "usertest", ""));
        Wallet wallet = new Wallet(1L, "1234-5", BigDecimal.valueOf(300), customer);

        Mockito.when(customerService.findById(Mockito.any())).thenReturn(customer);
        Mockito.when(walletRepository.findById(Mockito.any())).thenReturn(Optional.of(wallet));

        assertEquals(1L, walletService.getWallet(1L).getId());
    }

    @Test
    public void findByUserIdTest() {
        Customer customer = new Customer(1L, new User("user", "1234526845-9", "usertest@email.com", "usertest", ""));
        Wallet wallet = new Wallet(1L, "1234-5", BigDecimal.valueOf(300), customer);

        Mockito.when(walletRepository.findByCustomerId(Mockito.any())).thenReturn(Optional.of(wallet));

        assertEquals(1L, walletService.findByUserId(1L).getId());
    }
}
