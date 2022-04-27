package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
