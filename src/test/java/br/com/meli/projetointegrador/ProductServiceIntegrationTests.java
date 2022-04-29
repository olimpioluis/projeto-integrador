package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.dto.InboundOrderDTO;
import br.com.meli.projetointegrador.dto.ProductByBatchResponseImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ProductServiceIntegrationTests {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String getStandardInboundOrder_1() {
        return "{\n" +
                "    \"orderNumber\": 1,\n" +
                "    \"orderDate\": \"2020-01-05\",\n" +
                "    \"section\": {\n" +
                "        \"sectionCode\": 3,\n" +
                "        \"warehouseCode\": 1\n" +
                "    },\n" +
                "    \"batchStock\": [\n" +
                "        {\n" +
                "            \"productId\": 2,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": 10.5,\n" +
                "            \"initialQuantity\": 35,\n" +
                "            \"currentQuantity\": 35,\n" +
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-02-02\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 1,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": 10.5,\n" +
                "            \"initialQuantity\": 35,\n" +
                "            \"currentQuantity\": 35,\n" +
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-02-02\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    private String getStandardInboundOrder_2() {
        return "{\n" +
                "    \"orderNumber\": 2,\n" +
                "    \"orderDate\": \"2020-01-05\",\n" +
                "    \"section\": {\n" +
                "        \"sectionCode\": 3,\n" +
                "        \"warehouseCode\": 1\n" +
                "    },\n" +
                "    \"batchStock\": [\n" +
                "        {\n" +
                "            \"productId\": 1,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": 10.6,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2023-02-02\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 2,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": 10.5,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2023-02-02\"\n" +
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

    private String getProductById(Long productId, String orderBy, ResultMatcher resultMatcher) throws Exception {

        MvcResult response = mockmvc.perform(get("/api/v1/fresh-products/product")
                .contentType("application/json")
                .param("productId", String.valueOf(productId))
                .param("orderBy", orderBy))
                .andExpect(resultMatcher)
                .andReturn();

        return response.getResponse().getContentAsString();
    }


    @Test
    void getProductsThatHaveBatchTestOrder() throws Exception {
        String inboundOrderString_1 = getStandardInboundOrder_1();
        InboundOrderDTO inboundOrderDTO_1 = objectMapper.readValue(inboundOrderString_1, new TypeReference<>() {});
        postInboundOrder(inboundOrderDTO_1, status().isCreated());

        String inboundOrderString_2 = getStandardInboundOrder_2();
        InboundOrderDTO inboundOrderDTO_2 = objectMapper.readValue(inboundOrderString_2, new TypeReference<>() {});
        postInboundOrder(inboundOrderDTO_2, status().isCreated());


        String responseProducts = getProductById(1L, "F", status().isOk());
        List<ProductByBatchResponseImpl> productByBatchResponseDate = objectMapper.readValue(responseProducts,
                new TypeReference<>() {});

        assertEquals("2023-02-02", productByBatchResponseDate.get(0).getExpirationDate());
        assertEquals("2022-02-02", productByBatchResponseDate.get(1).getExpirationDate());


        responseProducts = getProductById(1L, "C", status().isOk());
        List<ProductByBatchResponseImpl> productByBatchResponseQuant = objectMapper.readValue(responseProducts,
                new TypeReference<>() {});

        assertAll(
                () -> assertEquals(35, productByBatchResponseQuant.get(0).getCurrentQuantity()),
                () -> assertEquals(20, productByBatchResponseQuant.get(1).getCurrentQuantity()),
                () -> assertEquals("2022-02-02", productByBatchResponseQuant.get(0).getExpirationDate())
        );

        responseProducts = getProductById(1L, "L", status().isOk());
        List<ProductByBatchResponseImpl> productByBatchResponseBatch = objectMapper.readValue(responseProducts,
                new TypeReference<>() {});

        assertAll(
                () -> assertEquals(BigInteger.valueOf(3), productByBatchResponseBatch.get(0).getBatchId()),
                () -> assertEquals(BigInteger.valueOf(2), productByBatchResponseBatch.get(1).getBatchId()),
                () -> assertEquals(20, productByBatchResponseBatch.get(0).getCurrentQuantity())
        );
    }

}
