package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.InboundOrderDTO;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.service.InboundOrderService;
import br.com.meli.projetointegrador.service.ProductService;
import br.com.meli.projetointegrador.service.SectionService;
import br.com.meli.projetointegrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<List<Batch>> postInboundOrder(@RequestBody InboundOrderDTO inboundOrderDTO) {
        return new ResponseEntity<>(inboundOrderService.save(InboundOrderDTO.map(inboundOrderDTO, sectionService, productService, warehouseService)), HttpStatus.CREATED);
    }

//    @PutMapping
//    public ResponseEntity<String> putInboundOrder(@RequestBody InboundOrderDTO inboundOrderDTO) {
//        return new ResponseEntity<>(inboundOrderService.update(inboundOrderDTO), HttpStatus.CREATED);
//    }
}
