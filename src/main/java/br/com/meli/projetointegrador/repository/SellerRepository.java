package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
