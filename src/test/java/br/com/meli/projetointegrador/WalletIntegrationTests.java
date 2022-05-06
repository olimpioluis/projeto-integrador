package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.dto.CartDTO;
import br.com.meli.projetointegrador.dto.InboundOrderDTO;
import br.com.meli.projetointegrador.dto.WalletDTO;
import br.com.meli.projetointegrador.dto.WalletTransactionDTO;
import br.com.meli.projetointegrador.model.Wallet;
import br.com.meli.projetointegrador.model.request.LoginRequest;
import br.com.meli.projetointegrador.model.response.JwtResponse;
import br.com.meli.projetointegrador.repository.WalletRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class WalletIntegrationTests {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WalletRepository walletRepository;

    private static boolean init = false;
    private static String CUSTOMER_JWT = "";

    private String postWalletBody() {
        return "{\n" +
                "    \"customerId\": 1,\n" +
                "    \"walletNumber\": \"12345-25\"\n" +
                "}";
    }

    private String putDepositBody() {
        return "{\n" +
                "    \"amount\": 300\n" +
                "}";
    }

    private String putWithdrawBody() {
        return "{\n" +
                "    \"amount\": 100\n" +
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

    public String putWithdrawWallet(WalletTransactionDTO transactionDTO, ResultMatcher resultMatcher, String jwt) throws Exception {
        MvcResult response = mockmvc.perform(put("/api/v1/fresh-products/wallet/withdraw/1")
                .contentType("application/json")
                .header("Authorization", "Bearer " + jwt)
                .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(resultMatcher)
                .andReturn();

        return response.getResponse().getContentAsString();
    }

    public String getWallet(ResultMatcher resultMatcher, String jwt) throws Exception {
        MvcResult response = mockmvc.perform(get("/api/v1/fresh-products/wallet/1")
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

            LoginRequest loginBody = new LoginRequest("customertest", "abcd1234");
            String signInResponse = signInPost(loginBody, status().isOk());
            JwtResponse jwtResponse = objectMapper.readValue(signInResponse, new TypeReference<>() {
            });
            CUSTOMER_JWT = jwtResponse.getToken();

            String walletDTOString = postWalletBody();
            WalletDTO walletDTO = objectMapper.readValue(walletDTOString, new TypeReference<>() {
            });

            postWallet(walletDTO, status().isCreated(), CUSTOMER_JWT);

            init = true;
        }
    }

    @Test
    void registerWallet() {
        Wallet wallet = walletRepository.findById(1L).orElse(new Wallet());

        assertAll(
                () -> assertEquals("customertest", wallet.getCustomer().getUser().getUsername()),
                () -> assertEquals(1L, wallet.getCustomer().getId())
        );
    }

    @Test
    void makeDeposit() throws Exception {
        String walletTransactionDTOString = putDepositBody();
        WalletTransactionDTO walletTransactionDTO = objectMapper.readValue(walletTransactionDTOString, new TypeReference<WalletTransactionDTO>() {
        });

        putDepositWallet(walletTransactionDTO, status().isOk(), CUSTOMER_JWT);

        Wallet wallet = walletRepository.findById(1L).orElse(new Wallet());

        assertEquals(BigDecimal.valueOf(300).setScale(2), wallet.getBalance());
    }

    @Test
    void makeWithdraw() throws Exception {
        String walletTransactionDTOString = putWithdrawBody();
        WalletTransactionDTO walletWithdrawDTO = objectMapper.readValue(walletTransactionDTOString, new TypeReference<WalletTransactionDTO>() {
        });

        putWithdrawWallet(walletWithdrawDTO, status().isOk(), CUSTOMER_JWT);

        Wallet wallet = walletRepository.findById(1L).orElse(new Wallet());

        assertEquals(BigDecimal.valueOf(200).setScale(2), wallet.getBalance());
    }

    @Test
    void getWallet() throws Exception {
        WalletDTO walletDTO = objectMapper.readValue(getWallet(status().isOk(), CUSTOMER_JWT), new TypeReference<WalletDTO>() {
        });

        Wallet wallet = walletRepository.findById(1L).orElse(new Wallet());

        assertEquals(walletDTO.getBalance(), wallet.getBalance());
    }
}
