package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.BatchStockDTO;
import br.com.meli.projetointegrador.dto.BatchStockDueDateDTO;
import br.com.meli.projetointegrador.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/due-date")
public class DueDateController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public ResponseEntity<List<BatchStockDueDateDTO>> getBatchByDueDate(@RequestParam Integer days, Long id) {
        return new ResponseEntity<>(BatchStockDueDateDTO.map(sectionService.checkBatchStockDueDate(id, days)), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<BatchStockDueDateDTO>> getBatchByDueDateWithCategory(@RequestParam Integer days, String category, String order) {
        return new ResponseEntity<>(BatchStockDueDateDTO.map(sectionService.checkBatchStockDueDateByCategory(days, category, order)), HttpStatus.OK);
    }
}
