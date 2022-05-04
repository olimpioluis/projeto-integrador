package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.StockManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockManagerRepository extends JpaRepository<StockManager, Long> {
     Optional<StockManager> findByUserUsername(String username);

}
