package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.ProductStockWarehouseDTO;
import br.com.meli.projetointegrador.dto.QuantityByWarehouseDTO;
import br.com.meli.projetointegrador.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe controladora responsável por lidar com as rotas referentes a classe Warehouse.
 * Possui rotas para listagem de produtos que estão em um armazem especifico.
 * @author Jeferson Barbosa Souza
 */
@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WarehouseController {

    @Autowired
    private BatchService batchService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_STOCK_MANAGER')")
    public ResponseEntity<ProductStockWarehouseDTO> getProductStockByWarehouse(@RequestParam Long productId) {

        List<Object[]> groupAllByWarehouseResult = batchService.getBatchStockByWarehouse(productId);
        List<QuantityByWarehouseDTO> quantityByWarehouseDTOList = new ArrayList<>();
/**
 * @see Casting only works for MySQL, for H2 tests, we have to use the constructor
 */
        for (Object[] item: groupAllByWarehouseResult) {
            String quantityString = item[0].toString();
            BigDecimal totalQuantity = new BigDecimal(quantityString);
            quantityByWarehouseDTOList.add(new QuantityByWarehouseDTO(totalQuantity, (BigInteger) item[1]));
        }

        ProductStockWarehouseDTO productStockWarehouseDTO = new ProductStockWarehouseDTO(productId, quantityByWarehouseDTOList);

        return new ResponseEntity<>(productStockWarehouseDTO, HttpStatus.OK);
    }
}
