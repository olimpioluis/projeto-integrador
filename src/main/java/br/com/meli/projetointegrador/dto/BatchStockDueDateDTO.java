package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class BatchStockDueDateDTO {
    private Long id;
    private Long productId;
    private String category;
    private LocalDate dueDate;
    private Integer quantity;

    public static BatchStockDueDateDTO map(Batch batch) {
        return new BatchStockDueDateDTO(
                batch.getId(),
                batch.getProduct().getId(),
                batch.getSection().getCategory().toString(),
                batch.getExpirationDate(),
                batch.getCurrentQuantity()
        );
    }

    public static List<BatchStockDueDateDTO> map(List<Batch> batches) {
        return batches.stream().map(BatchStockDueDateDTO::map).collect(Collectors.toList());
    }
}
