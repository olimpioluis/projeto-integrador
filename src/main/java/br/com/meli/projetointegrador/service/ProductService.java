package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Product;

public interface ProductService {
    Product findById(Long id);
}
