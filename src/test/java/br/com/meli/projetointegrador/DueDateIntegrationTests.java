package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.dto.BatchStockDueDateDTO;
import br.com.meli.projetointegrador.dto.InboundOrderDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DueDateIntegrationTests {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

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
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-03-27\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 2,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": -10.5,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-05-27\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 3,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": -10.5,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-06-27\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"productId\": 3,\n" +
                "            \"currentTemperature\": 25.5,\n" +
                "            \"minTemperature\": -10.5,\n" +
                "            \"initialQuantity\": 20,\n" +
                "            \"currentQuantity\": 20,\n" +
                "            \"manufacturingDate\": \"2022-01-01\",\n" +
                "            \"manufacturingTime\": \"2022-01-01T00:00:00\",\n" +
                "            \"expirationDate\": \"2022-07-27\"\n" +
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

    private String getCheckBatchStockDueDate(String days, String sectionId,ResultMatcher resultMatcher) throws Exception {

        MvcResult response = mockmvc.perform(get("/api/v1/fresh-products/due-date")
                .param("days", days)
                .param("id", sectionId))
                .andExpect(resultMatcher)
                .andReturn();


        return response.getResponse().getContentAsString();
    }

    @Test
    void getBatchStockByDueDate() throws Exception {

        String inboundOrderString = getStandardInboundOrder();
        InboundOrderDTO inboundOrderDTO = objectMapper.readValue(inboundOrderString, new TypeReference<>() {});

        postInboundOrder(inboundOrderDTO, status().isCreated());

        String checkBatchStockDueDate = getCheckBatchStockDueDate("64", "1",status().isOk());

        List<BatchStockDueDateDTO> batchStockDueDateDTOList = objectMapper.readValue(checkBatchStockDueDate, new TypeReference<>() {});

        assertEquals(2, batchStockDueDateDTOList.size());
    }

    private String getCheckBatchStockDueDateWithCategory(String days, String category, String order, ResultMatcher resultMatcher) throws Exception {

        MvcResult response = mockmvc.perform(get("/api/v1/fresh-products/due-date/list")
                .param("days", days)
                .param("category", category)
                .param("order", order)
                )
                .andExpect(resultMatcher)
                .andReturn();


        return response.getResponse().getContentAsString();
    }

    @Test
    void getBatchStockByDueDateWithCategory() throws Exception {

        String checkBatchStockDueDateWithCategory1 = getCheckBatchStockDueDateWithCategory("95", "FROZEN", "asc", status().isOk());
        String checkBatchStockDueDateWithCategory2 = getCheckBatchStockDueDateWithCategory("95", "FROZEN", "desc", status().isOk());

        List<BatchStockDueDateDTO> batchStockDueDateDTOList1 = objectMapper.readValue(checkBatchStockDueDateWithCategory1, new TypeReference<>() {});
        List<BatchStockDueDateDTO> batchStockDueDateDTOList2 = objectMapper.readValue(checkBatchStockDueDateWithCategory2, new TypeReference<>() {});

        assertEquals(3, batchStockDueDateDTOList1.size());
        assertEquals(3, batchStockDueDateDTOList2.size());

        assertEquals(2, batchStockDueDateDTOList1.get(0).getId());
        assertEquals(4, batchStockDueDateDTOList2.get(0).getId());

    }
}
