package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
