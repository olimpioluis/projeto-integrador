package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.dto.*;
import br.com.meli.projetointegrador.repository.BatchRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class WarehouseIntegrationTests {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BatchRepository batchRepository;

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
                "            \"minTemperature\": 10.6,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-02-02\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 2,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": 10.5,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-02-02\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 2,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": 10.7,\n" +
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

    private String getProductsByWarehouse(Long productId, ResultMatcher resultMatcher) throws Exception{
        MvcResult result = mockmvc.perform(get("/api/v1/fresh-products/warehouse")
                .contentType("application/json")
                .param("productId", String.valueOf(productId)))
                .andExpect(resultMatcher)
                .andReturn();

        return result.getResponse().getContentAsString();
    }


    @Test
    void getValidProductStockByWarehouse() throws Exception {
        String inboundOrderString = getStandardInboundOrder();
        InboundOrderDTO inboundOrderDTO = objectMapper.readValue(inboundOrderString, new TypeReference<>() {});
        postInboundOrder(inboundOrderDTO, status().isCreated());


        SectionDTO inboundSectionDto = inboundOrderDTO.getSection();
        inboundSectionDto.setSectionCode(3L);
        inboundSectionDto.setWarehouseCode(1L);
        inboundOrderDTO.setSection(inboundSectionDto);

        List<BatchStockDTO> batchStockDTOList = inboundOrderDTO.getBatchStock();
        BatchStockDTO batchStockDTO = batchStockDTOList.get(2);
        batchStockDTO.setMinTemperature(10.8);
        batchStockDTOList.add(batchStockDTO);

        inboundOrderDTO.setBatchStock(batchStockDTOList);
        postInboundOrder(inboundOrderDTO, status().isCreated());


        String resultStr = getProductsByWarehouse(1L, status().isOk());
        ProductStockWarehouseDTO productByWarehouse = objectMapper.readValue(resultStr, new TypeReference<>() {});


        List<QuantityByWarehouseDTO> quantityByWarehouseDTOList = Arrays.asList(new QuantityByWarehouseDTO(BigDecimal.valueOf(20), BigInteger.valueOf(1)),
                new QuantityByWarehouseDTO(BigDecimal.valueOf(20), BigInteger.valueOf(2)));
        ProductStockWarehouseDTO productByWarehouseTest = new ProductStockWarehouseDTO(1L, quantityByWarehouseDTOList);


        assertEquals(productByWarehouse.getQuantityByWarehouseList().toString(), productByWarehouseTest.getQuantityByWarehouseList().toString());


        resultStr = getProductsByWarehouse(2L, status().isOk());
        productByWarehouse = objectMapper.readValue(resultStr, new TypeReference<>() {});

        quantityByWarehouseDTOList = Arrays.asList(new QuantityByWarehouseDTO(BigDecimal.valueOf(60), BigInteger.valueOf(1)),
                new QuantityByWarehouseDTO(BigDecimal.valueOf(40), BigInteger.valueOf(2)));
        productByWarehouseTest = new ProductStockWarehouseDTO(2L, quantityByWarehouseDTOList);

        assertEquals(productByWarehouse.getQuantityByWarehouseList().toString(), productByWarehouseTest.getQuantityByWarehouseList().toString());
    }

    @Test
    void getInvalidProductStockByWarehouse() throws Exception {
        String resultStr = getProductsByWarehouse(333L, status().isNotFound());
        ErrorDTO errorDTO = objectMapper.readValue(resultStr, new TypeReference<>() {});

        assertEquals("The Product ID: 333 does not exists!", errorDTO.getDescription());
    }


}
