package br.com.meli.projetointegrador;


import br.com.meli.projetointegrador.dto.InboundOrderDTO;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.repository.BatchRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class InboundOrderIntegrationTests {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BatchRepository batchRepository;

    @Test
    void registerValidInboundOrder() throws Exception {

        String inboundOrderString = "{\n" +
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

        InboundOrderDTO inboundOrderDTO = objectMapper.readValue(inboundOrderString, new TypeReference<>() {});

        mockmvc.perform(post("/api/v1/fresh-products/inboundorder")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(inboundOrderDTO)))
                .andExpect(status().isCreated());

        Batch batch = batchRepository.findBySectionId(6L);

        assertAll(
                () -> assertEquals(25.5, batch.getCurrentTemperature()),
                () -> assertEquals(20, batch.getInitialQuantity())
        );

    }

    @Test
    void InboundOrderInvalidSection() throws Exception {

        String inboundOrderString = "{\n" +
                "    \"orderNumber\": 2,\n" +
                "    \"orderDate\": \"2020-01-05\",\n" +
                "    \"section\": {\n" +
                "        \"sectionCode\": 666,\n" +
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

        InboundOrderDTO inboundOrderDTO = objectMapper.readValue(inboundOrderString, new TypeReference<>() {});

        MvcResult reponseController = mockmvc.perform(post("/api/v1/fresh-products/inboundorder")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inboundOrderDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();

        // Validar ErrorDTO retornado para InexistentSectionException

    }

}
