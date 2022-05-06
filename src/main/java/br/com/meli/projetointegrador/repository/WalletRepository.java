package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCustomerId(Long userId);
}
