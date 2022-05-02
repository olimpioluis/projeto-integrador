package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.dto.BatchStockDTO;
import br.com.meli.projetointegrador.dto.InboundOrderDTO;
import br.com.meli.projetointegrador.dto.ProductDTOi;
import br.com.meli.projetointegrador.dto.ProductDTOiImpl;
import br.com.meli.projetointegrador.model.Product;
import br.com.meli.projetointegrador.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {
    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    private String getStandardInboundOrder() {
        return "{\n" +
                "    \"orderNumber\": 2,\n" +
                "    \"orderDate\": \"2020-01-05\",\n" +
                "    \"section\": {\n" +
                "        \"sectionCode\": 6,\n" +
                "        \"warehouseCode\": 2\n" +
                "    },\n" +
                "    \"batchStock\": [\n" +
                "        {\n" +
                "            \"productId\": 1,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": 10.5,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-02-02\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    private String postInboundOrder(InboundOrderDTO inboundOrderDTO, ResultMatcher resultMatcher) throws Exception {

        MvcResult response = mockmvc.perform(post("/api/v1/fresh-products/inboundorder")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inboundOrderDTO)))
                .andExpect(resultMatcher)
                .andReturn();

        return response.getResponse().getContentAsString();
    }

    private String getAllProduct() throws Exception {

        MvcResult response = mockmvc.perform(get("/api/v1/fresh-products/"))
                .andExpect(status().isOk())
                .andReturn();

        return response.getResponse().getContentAsString();
    }
    private String getAllProductByCategory() throws Exception {

        MvcResult response = mockmvc.perform(get("/api/v1/fresh-products/list/").param("category","FR"))
                .andExpect(status().isOk())
                .andReturn();

        return response.getResponse().getContentAsString();
    }


    @BeforeEach
    void initialSetup() throws Exception {

        String inboundOrderString = getStandardInboundOrder();
        InboundOrderDTO inboundOrderDTO = objectMapper.readValue(inboundOrderString, new TypeReference<>() {
        });

        postInboundOrder(inboundOrderDTO, status().isCreated());
    }

    @Test
    void existentProductList() throws Exception {

        List<ProductDTOi> productDTOis = productRepository.findAllByBatchListExists();

        List<ProductDTOiImpl> productDTOiList = objectMapper.readValue(getAllProduct(), new TypeReference<>() {
        });

        assertEquals(productDTOis.size(), productDTOiList.size());

    }

    @Test
    void validProductListByCategory() throws Exception {

        List<ProductDTOi> productDTOis = productRepository.findAllByBatchListExistsBySection("FROZEN");

        List<ProductDTOi> productDTOiList = objectMapper.readValue(getAllProductByCategory(), new TypeReference<>() {
        });

        assertEquals(productDTOis.size(), productDTOiList.size());

    }

}
