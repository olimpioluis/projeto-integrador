package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
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

    public static List<BatchStockDTO> map(List<Batch> batches) {
        return batches.stream().map(batch -> new BatchStockDTO(
                batch.getProduct().getId(),
                batch.getCurrentTemperature(),
                batch.getMinTemperature(),
                batch.getInitialQuantity(),
                batch.getCurrentQuantity(),
                batch.getManufacturingDate(),
                batch.getManufacturingTime(),
                batch.getExpirationDate()
        )).collect(Collectors.toList());
    }
}
