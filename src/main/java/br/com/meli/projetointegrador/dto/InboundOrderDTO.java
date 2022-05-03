package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.InboundOrder;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.model.Warehouse;
import br.com.meli.projetointegrador.service.ProductService;
import br.com.meli.projetointegrador.service.SectionService;
import br.com.meli.projetointegrador.service.WarehouseService;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class InboundOrderDTO {

    @NotNull(message = "OrderNumber missing.")
    private Integer orderNumber;
    @NotNull(message = "OrderDate missing.")
    private LocalDate orderDate;
    @Valid
    @NotNull(message = "Section missing.")
    private SectionDTO section;
    @NotNull(message = "BatchStock missing.")
    private List<@Valid BatchStockDTO> batchStock;

    public static InboundOrder map(InboundOrderDTO inboundOrderDTO, SectionService sectionService, ProductService productService, WarehouseService warehouseService) {
        Section section = sectionService.findById(inboundOrderDTO.getSection().getSectionCode());
        Warehouse warehouse = warehouseService.findById(inboundOrderDTO.getSection().getWarehouseCode());

        List<Batch> batches = inboundOrderDTO.batchStock.stream().map(batch -> BatchStockDTO.map(batch, productService, section)).collect(Collectors.toList());

        return InboundOrder.builder()
                .orderNumber(inboundOrderDTO.getOrderNumber())
                .orderDate(inboundOrderDTO.getOrderDate())
                .section(new Section(section.getId(), section.getName(), section.getCategory(), section.getSize(), section.getCurrentSize(), warehouse, section.getBatchList()))
                .batchList(batches)
                .build();
    }
}
