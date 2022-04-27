package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.InboundOrder;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.service.BatchService;
import br.com.meli.projetointegrador.service.ProductService;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class BatchStockPutDTO {
    private Long id;
    private Long productId;
    private Double currentTemperature;
    private Double minTemperature;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate expirationDate;

    public static Batch map(BatchStockPutDTO batchStockPutDTO, ProductService productService, BatchService batchService, InboundOrder inboundOrder) {
        if (Objects.isNull(batchStockPutDTO.getId())) {
            return Batch.builder()
                    .product(productService.findById(batchStockPutDTO.getProductId()))
                    .currentTemperature(batchStockPutDTO.getCurrentTemperature())
                    .minTemperature(batchStockPutDTO.getMinTemperature())
                    .initialQuantity(batchStockPutDTO.getInitialQuantity())
                    .currentQuantity(batchStockPutDTO.getCurrentQuantity())
                    .manufacturingDate(batchStockPutDTO.getManufacturingDate())
                    .manufacturingTime(batchStockPutDTO.getManufacturingTime())
                    .expirationDate(batchStockPutDTO.getExpirationDate())
                    .section(inboundOrder.getSection())
                    .inboundOrder(inboundOrder)
                    .build();
        }

        Batch batch = batchService.findById(batchStockPutDTO.getId());

        batch.setManufacturingDate(Objects.isNull(batchStockPutDTO.getManufacturingDate()) ? batch.getManufacturingDate() : batchStockPutDTO.getManufacturingDate());
        batch.setManufacturingTime(Objects.isNull(batchStockPutDTO.getManufacturingTime()) ? batch.getManufacturingTime() : batchStockPutDTO.getManufacturingTime());
        batch.setExpirationDate(Objects.isNull(batchStockPutDTO.getExpirationDate()) ? batch.getExpirationDate() : batchStockPutDTO.getExpirationDate());

        return batch;
    }
}
