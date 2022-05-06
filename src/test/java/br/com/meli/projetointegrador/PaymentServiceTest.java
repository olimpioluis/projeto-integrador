package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.model.*;
import br.com.meli.projetointegrador.repository.PaymentRepository;
import br.com.meli.projetointegrador.security.services.UserDetailsImpl;
import br.com.meli.projetointegrador.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceTest {

    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private BatchService batchService;
    @Mock
    private ProductService productService;
    @Mock
    private WalletService walletService;
    @Mock
    private CustomerService customerService;

    @BeforeEach
    private void initializePaymentService() {
        MockitoAnnotations.openMocks(this);
        this.paymentService = new PaymentServiceImpl(paymentRepository, batchService, productService, walletService, customerService);
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
        Payment payment = new Payment(1L, PaymentStatus.PENDING, BigDecimal.valueOf(100), new Customer(), new Cart());

        Mockito.when(paymentRepository.save(Mockito.any())).thenReturn(payment);

        assertEquals(1L, paymentService.save(payment).getId());
    }

    @Test
    public void findByIdTest() {
        Payment payment = new Payment(1L, PaymentStatus.PENDING, BigDecimal.valueOf(100), new Customer(), new Cart());

        Mockito.when(paymentRepository.findById(Mockito.any())).thenReturn(Optional.of(payment));

        assertEquals(1L, paymentService.findById(1L).getId());
    }

    @Test
    public void payTest() {
        initializeAuthentication();

        Product product = new Product(1L, "Produto 1", 100.0, 2.0, 2.0, Arrays.asList(new Batch(), new Batch()));
        Advertisement advertisement = new Advertisement(1L, "Advertisement 1", BigDecimal.valueOf(100), product, new Seller());
        Item item = new Item(1L, advertisement, new Cart(), 1);

        Cart cart = new Cart(1L, LocalDate.of(2022, 1, 1), new Customer(), BigDecimal.valueOf(0), new OrderStatus(1L, StatusCode.CART), Arrays.asList(item));

        Customer customer = new Customer(1L, new User("user", "1234526845-9", "usertest@email.com", "usertest", ""));

        Wallet wallet = new Wallet(1L, "1234-5", BigDecimal.valueOf(300), customer);
        Payment payment = new Payment(1L, PaymentStatus.PENDING, BigDecimal.valueOf(100), customer, cart);
        List<Batch> batchList = Arrays.asList(new Batch(), new Batch());

        Mockito.when(paymentRepository.findById(Mockito.any())).thenReturn(Optional.of(payment));
        Mockito.when(batchService.getBatchesWithExpirationDateGreaterThan3Weeks(Mockito.any())).thenReturn(batchList);
        Mockito.when(productService.getTotalQuantity(Mockito.any())).thenReturn(10);
        Mockito.when(customerService.findByUserUsername(Mockito.any())).thenReturn(customer);
        Mockito.when(walletService.findByUserId(Mockito.any())).thenReturn(wallet);
        Mockito.when(walletService.withdraw(Mockito.any(), Mockito.any())).thenReturn(wallet);
        Mockito.when(paymentRepository.save(Mockito.any())).thenReturn(payment);

        assertAll(
                () -> assertEquals(1L, paymentService.pay(1L).getId()),
                () -> assertEquals(PaymentStatus.PAID, paymentService.pay(1L).getStatus())
        );
    }

    @Test
    public void getPaymentTest() {
        initializeAuthentication();

        Customer customer = new Customer(1L, new User("user", "1234526845-9", "usertest@email.com", "usertest", ""));
        Payment payment = new Payment(1L, PaymentStatus.PENDING, BigDecimal.valueOf(100), customer, new Cart());

        Mockito.when(paymentRepository.findById(Mockito.any())).thenReturn(Optional.of(payment));
        Mockito.when(customerService.findByUserUsername(Mockito.any())).thenReturn(customer);

        assertEquals(1L, paymentService.getPayment(1L).getId());
    }
}
