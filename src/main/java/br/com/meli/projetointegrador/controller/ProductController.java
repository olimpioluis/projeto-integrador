package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.ProductDTO;
import br.com.meli.projetointegrador.model.Product;
import br.com.meli.projetointegrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe controladora responsável por lidar com as rotas referentes a classe product.
 * Possui rotas para listagem de produtos.
 *
 * @author Lucas Troleiz Lopes
 */
@RestController
@RequestMapping("/api/v1/fresh-products/")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Método responsável por listar os produtos do repositorio
     */
    @GetMapping
    public List<ProductDTO> productListAll() {
          return ProductDTO.converter(productService.findAll());
    }
}
