package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.model.Batch;
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

    @PostMapping
    public ResponseEntity<List<Batch>> postInboundOrder(@RequestBody InboundOrderDTO inboundOrderDTO) {
        return new ResponseEntity<>(inboundOrderService.save(inboundOrderDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> putInboundOrder(@RequestBody InboundOrderDTO inboundOrderDTO) {
        return new ResponseEntity<>(inboundOrderService.update(inboundOrderDTO), HttpStatus.CREATED);
    }
}
