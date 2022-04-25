package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.StockManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockManagerRepository extends JpaRepository<StockManager, Long> {
}
