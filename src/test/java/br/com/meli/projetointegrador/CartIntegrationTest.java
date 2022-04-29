package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.dto.InboundOrderDTO;
import br.com.meli.projetointegrador.repository.CartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class CartIntegrationTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CartRepository cartRepository;



}
