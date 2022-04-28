package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.dto.ProductDTOi;
import br.com.meli.projetointegrador.model.Product;

import java.util.List;

public interface ProductService {
    Product findById(Long id);

    List<ProductDTOi> findAllByBatchListExists();

    List<ProductDTOi> findAllByBatchListExistsBySection(String category);

    Integer getTotalQuantity(Long id);

}
