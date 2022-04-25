package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.service.ProductService;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class BatchStockDTO {
    private Long productId;
    private Double currentTemperature;
    private Double minTemperature;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate expirationDate;

    public static Batch map(BatchStockDTO batchStockDTO, ProductService productService, Section section) {
        return Batch.builder()
                .product(productService.findById(batchStockDTO.getProductId()))
                .currentTemperature(batchStockDTO.getCurrentTemperature())
                .minTemperature(batchStockDTO.getMinTemperature())
                .initialQuantity(batchStockDTO.getInitialQuantity())
                .currentQuantity(batchStockDTO.getCurrentQuantity())
                .manufacturingDate(batchStockDTO.getManufacturingDate())
                .manufacturingTime(batchStockDTO.getManufacturingTime())
                .expirationDate(batchStockDTO.getExpirationDate())
                .section(section)
                .build();
    }
}
