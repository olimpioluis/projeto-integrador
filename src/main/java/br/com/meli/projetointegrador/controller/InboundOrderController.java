package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.BatchStockDTO;
import br.com.meli.projetointegrador.dto.InboundOrderDTO;
import br.com.meli.projetointegrador.dto.InboundOrderPutDTO;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    private InboundOrderService inboundOrderService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private BatchService batchService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_STOCK_MANAGER')")
    public ResponseEntity<List<BatchStockDTO>> postInboundOrder(@RequestBody InboundOrderDTO inboundOrderDTO) {
        return new ResponseEntity<>(BatchStockDTO.map(inboundOrderService.save(InboundOrderDTO.map(inboundOrderDTO, sectionService, productService, warehouseService))), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_STOCK_MANAGER')")
    public ResponseEntity<List<BatchStockDTO>> putInboundOrder(@RequestBody InboundOrderPutDTO inboundOrderPutDTO) {
        return new ResponseEntity<>(BatchStockDTO.map(inboundOrderService.update(InboundOrderPutDTO.map(inboundOrderPutDTO, inboundOrderService, sectionService, warehouseService, productService, batchService))), HttpStatus.CREATED);
    }
}
