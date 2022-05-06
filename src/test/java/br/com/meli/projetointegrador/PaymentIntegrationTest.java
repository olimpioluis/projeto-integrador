package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.dto.*;
import br.com.meli.projetointegrador.model.Payment;
import br.com.meli.projetointegrador.model.PaymentStatus;
import br.com.meli.projetointegrador.model.Wallet;
import br.com.meli.projetointegrador.model.request.LoginRequest;
import br.com.meli.projetointegrador.model.response.JwtResponse;
import br.com.meli.projetointegrador.repository.PaymentRepository;
import br.com.meli.projetointegrador.service.WalletService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class PaymentIntegrationTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private WalletService walletService;

    private static boolean init = false;
    private static String CUSTOMER_JWT = "";
    private static String STOCK_MANAGER_JWT = "";

    private String getStandardInboundOrder() {
        return "{\n" +
                "    \"orderNumber\": 10,\n" +
                "    \"orderDate\": \"2022-04-27\",\n" +
                "    \"section\": {\n" +
                "        \"sectionCode\": 1,\n" +
                "        \"warehouseCode\": 1\n" +
                "    },\n" +
                "    \"batchStock\": [\n" +
                "        {\n" +
                "            \"productId\": 1,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": -10.5,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "            \"manufacturingDate\": \"2022-05-01\",\n" +
                "            \"manufacturingTime\": \"2022-05-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-09-09\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 2,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": -10.5,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "             \"manufacturingDate\": \"2022-05-01\",\n" +
                "            \"manufacturingTime\": \"2022-05-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-09-09\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 3,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": -10.5,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "             \"manufacturingDate\": \"2022-05-01\",\n" +
                "            \"manufacturingTime\": \"2022-05-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-09-09\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 3,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": -10.5,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "             \"manufacturingDate\": \"2022-05-01\",\n" +
                "            \"manufacturingTime\": \"2022-05-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-09-09\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    public String postPurchaseOrder() {
        return "{\n" +
                "    \"orderDate\": \"2022-02-02\",\n" +
                "    \"customerId\": 1,\n" +
                "    \"orderStatus\": {\n" +
                "        \"statusCode\": \"CART\"\n" +
                "    },\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"advertisementId\": 1,\n" +
                "            \"quantity\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"advertisementId\": 2,\n" +
                "            \"quantity\": 2\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    public String signUpCustomerBody() {
        return "{\n" +
                "    \"name\" : \"customertest\",\n" +
                "    \"username\" : \"customertest\",\n" +
                "    \"email\" : \"customertest@teste.com.br\",\n" +
                "    \"cpf\" : \"000-000-000-03\",\n" +
                "    \"password\" : \"abcd1234\",\n" +
                "    \"role\" : [\"customer\"]\n" +
                "}";
    }

    public String signUpStockManagerBody() {
        return "{\n" +
                "    \"name\" : \"stockmanagertest\",\n" +
                "    \"username\" : \"stockmanagertest\",\n" +
                "    \"email\" : \"stockmanagertest@teste.com.br\",\n" +
                "    \"cpf\" : \"000-000-000-01\",\n" +
                "    \"password\" : \"abcd1234\",\n" +
                "    \"warehouse_id\": 1,\n" +
                "    \"role\" : [\"manager\"]\n" +
                "}";
    }

    public void signUpPost(ResultMatcher resultMatcher, String signUpDTO) throws Exception {

        mockmvc.perform(post("/api/auth/signup")
                .contentType("application/json")
                .content(signUpDTO))
                .andExpect(resultMatcher);

    }

    public String signInPost(LoginRequest loginRequest, ResultMatcher resultMatcher) throws Exception {

        MvcResult result = mockmvc.perform(post("/api/auth/signin")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(resultMatcher).andReturn();

        return result.getResponse().getContentAsString();

    }

    private String postInboundOrder(InboundOrderDTO inboundOrderDTO, ResultMatcher resultMatcher, String jwt) throws Exception {

        MvcResult response = mockmvc.perform(post("/api/v1/fresh-products/inboundorder")
                .contentType("application/json")
                .header("Authorization", "Bearer " + jwt)
                .content(objectMapper.writeValueAsString(inboundOrderDTO)))
                .andExpect(resultMatcher)
                .andReturn();

        return response.getResponse().getContentAsString();
    }

    private String postPurchaseOrder(CartDTO cartDTO, ResultMatcher resultMatcher, String jwt) throws Exception {

        MvcResult response = mockmvc.perform(post("/api/v1/fresh-products/orders")
                .contentType("application/json")
                .header("Authorization", "Bearer " + jwt)
                .content(objectMapper.writeValueAsString(cartDTO)))
                .andExpect(resultMatcher)
                .andReturn();

        return response.getResponse().getContentAsString();
    }

    private String putPurchaseOrder(ResultMatcher resultMatcher, String jwt) throws Exception {

        MvcResult response = mockmvc.perform(put("/api/v1/fresh-products/orders/1")
                .header("Authorization", "Bearer " + jwt))
                .andExpect(resultMatcher)
                .andReturn();

        return response.getResponse().getContentAsString();
    }

    private String postWalletBody() {
        return "{\n" +
                "    \"customerId\": 1,\n" +
                "    \"walletNumber\": \"12345-25\"\n" +
                "}";
    }

    private String putDepositBody() {
        return "{\n" +
                "    \"amount\": 120\n" +
                "}";
    }

    public String postWallet(WalletDTO walletDTO, ResultMatcher resultMatcher, String jwt) throws Exception {
        MvcResult response = mockmvc.perform(post("/api/v1/fresh-products/wallet")
                .contentType("application/json")
                .header("Authorization", "Bearer " + jwt)
                .content(objectMapper.writeValueAsString(walletDTO)))
                .andExpect(resultMatcher)
                .andReturn();

        return response.getResponse().getContentAsString();
    }

    public String putDepositWallet(WalletTransactionDTO transactionDTO, ResultMatcher resultMatcher, String jwt) throws Exception {
        MvcResult response = mockmvc.perform(put("/api/v1/fresh-products/wallet/deposit/1")
                .contentType("application/json")
                .header("Authorization", "Bearer " + jwt)
                .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(resultMatcher)
                .andReturn();

        return response.getResponse().getContentAsString();
    }

    public String putFinishPayment(ResultMatcher resultMatcher, String jwt) throws Exception {
        MvcResult response = mockmvc.perform(put("/api/v1/fresh-products/payment/1")
                .contentType("application/json")
                .header("Authorization", "Bearer " + jwt))
                .andExpect(resultMatcher)
                .andReturn();

        return response.getResponse().getContentAsString();
    }

    public String getPayment(ResultMatcher resultMatcher, String jwt) throws Exception {
        MvcResult response = mockmvc.perform(get("/api/v1/fresh-products/payment/1")
                .header("Authorization", "Bearer " + jwt))
                .andExpect(resultMatcher)
                .andReturn();

        return response.getResponse().getContentAsString();
    }

    @BeforeEach
    void initialSetup() throws Exception {

        if (!init) {

            String signUpDTOCustomer = signUpCustomerBody();
            signUpPost(status().isOk(), signUpDTOCustomer);

            String signUpDTOStockManager = signUpStockManagerBody();
            signUpPost(status().isOk(), signUpDTOStockManager);

            LoginRequest loginBody = new LoginRequest("customertest", "abcd1234");
            String signInResponse = signInPost(loginBody, status().isOk());
            JwtResponse jwtResponse = objectMapper.readValue(signInResponse, new TypeReference<>() {
            });
            CUSTOMER_JWT = jwtResponse.getToken();

            loginBody = new LoginRequest("stockmanagertest", "abcd1234");
            signInResponse = signInPost(loginBody, status().isOk());
            jwtResponse = objectMapper.readValue(signInResponse, new TypeReference<>() {
            });
            STOCK_MANAGER_JWT = jwtResponse.getToken();

            String walletDTOString = postWalletBody();
            WalletDTO walletDTO = objectMapper.readValue(walletDTOString, new TypeReference<>() {
            });

            String inboundOrderString = getStandardInboundOrder();
            String purchaseOrderString = postPurchaseOrder();
            CartDTO cartDTO = objectMapper.readValue(purchaseOrderString, new TypeReference<CartDTO>() {
            });

            InboundOrderDTO inboundOrderDTO = objectMapper.readValue(inboundOrderString, new TypeReference<>() {
            });

            postInboundOrder(inboundOrderDTO, status().isCreated(), STOCK_MANAGER_JWT);
            postPurchaseOrder(cartDTO, status().isCreated(), CUSTOMER_JWT);
            putPurchaseOrder(status().isOk(), CUSTOMER_JWT);

            postWallet(walletDTO, status().isCreated(), CUSTOMER_JWT);

            init = true;
        }
    }

    @Test
    public void makePayment() throws Exception {
        String walletTransactionDTOString = putDepositBody();
        WalletTransactionDTO walletTransactionDTO = objectMapper.readValue(walletTransactionDTOString, new TypeReference<WalletTransactionDTO>() {
        });

        putDepositWallet(walletTransactionDTO, status().isOk(), CUSTOMER_JWT);
        putFinishPayment(status().isOk(), CUSTOMER_JWT);

        Payment payment = paymentRepository.findById(1L).orElse(new Payment());
        Wallet wallet = walletService.findById(1L);

        assertAll(
                () -> assertEquals(PaymentStatus.PAID, payment.getStatus()),
                () -> assertEquals(BigDecimal.valueOf(0).setScale(2), wallet.getBalance())
        );
    }

    @Test
    public void makePaymentWithoutEnoughBalance() throws Exception {
        Wallet wallet = walletService.findById(1L);

        String responseStr = putFinishPayment(status().isBadRequest(), CUSTOMER_JWT);
        ErrorDTO errorDto = objectMapper.readValue(responseStr, new TypeReference<>() {});

        assertAll(
                () -> assertEquals("Insufficient balance in the account to make the payment.", errorDto.getDescription()),
                () -> assertEquals("InsufficientBalanceException", errorDto.getName()),
                () -> assertEquals(BigDecimal.valueOf(0).setScale(2), wallet.getBalance())
        );
    }

    @Test
    public void getPayment() throws Exception {
        String responseStr = getPayment(status().isOk(), CUSTOMER_JWT);
        PaymentDTO paymentDTO = objectMapper.readValue(responseStr, new TypeReference<>() {});

        assertAll(
                () -> assertEquals(1L, paymentDTO.getId()),
                () -> assertEquals(PaymentStatus.PENDING, paymentDTO.getPaymentStatus())
        );
    }

}
