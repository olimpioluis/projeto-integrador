package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.ProductByBatchResponse;
import br.com.meli.projetointegrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductByBatchResponse>> getProductById(@RequestParam(name = "productId") Long productId,
                                                                       @RequestParam(name = "orderBy", required = false, defaultValue = "L") String orderBy) {

        return new ResponseEntity<>(productService.getAllProductThatHaveBatch(productId, orderBy), HttpStatus.OK);
    }

}
