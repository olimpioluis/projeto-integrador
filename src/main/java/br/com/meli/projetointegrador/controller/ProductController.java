package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.ProductByBatchResponse;
import br.com.meli.projetointegrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.meli.projetointegrador.dto.ProductDTOi;
import br.com.meli.projetointegrador.model.Category;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Classe controladora responsável por lidar com as rotas referentes a classe product.
 * Possui rotas para listagem de produtos.
 * @author Jeferson Barbosa Souza
 * @author Lucas Troleiz Lopes
 */
@RestController
@RequestMapping("/api/v1/fresh-products/")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    @PreAuthorize("hasRole('ROLE_STOCK_MANAGER')")
    public ResponseEntity<List<ProductByBatchResponse>> getProductById(@RequestParam(name = "productId") Long productId,
                                                                       @RequestParam(name = "orderBy", required = false, defaultValue = "L") String orderBy) {

        return new ResponseEntity<>(productService.getAllProductThatHaveBatch(productId, orderBy), HttpStatus.OK);
    }

    /**
     * Método responsável por listar os produtos do repositorio
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public List<ProductDTOi> productListAll() {
        return productService.findAllByBatchListExists();
    }

    /**
     * Método responsável por listar os produtos do repositorio por categoria
     */
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public List<ProductDTOi> productListAllByCategory(@RequestParam String category) {
        Category category1;
        switch (category){
            case "FS": category1 = Category.FRESH;
                break;
            case "RF": category1 = Category.REFRIGERATED;
                break;
            default: category1 = Category.FROZEN;
                break;
        }
        return productService.findAllByBatchListExistsBySection(category1.toString());
    }

}
