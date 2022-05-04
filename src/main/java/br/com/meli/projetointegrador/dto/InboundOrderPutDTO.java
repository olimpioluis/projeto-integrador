package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.InboundOrder;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.model.Warehouse;
import br.com.meli.projetointegrador.service.*;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class InboundOrderPutDTO {

    @NotNull(message = "InboundOrderId missing.")
    private Long inboundOrderId;

    private LocalDate orderDate;

    private SectionDTO section;

    private List<BatchStockPutDTO> batchStock;

    public static InboundOrder map(InboundOrderPutDTO inboundOrderPutDTO, InboundOrderService inboundOrderService, SectionService sectionService, WarehouseService warehouseService, ProductService productService, BatchService batchService) {
        InboundOrder inboundOrder = inboundOrderService.findById(inboundOrderPutDTO.getInboundOrderId());

        List<Batch> batches = inboundOrderPutDTO.batchStock.stream().map(batch -> BatchStockPutDTO.map(batch, productService, batchService, inboundOrder)).collect(Collectors.toList());

        inboundOrder.setOrderDate(Objects.isNull(inboundOrderPutDTO.getOrderDate()) ? inboundOrder.getOrderDate() : inboundOrderPutDTO.getOrderDate());
        inboundOrder.setBatchList(batches);

        return inboundOrder;

    }
}
