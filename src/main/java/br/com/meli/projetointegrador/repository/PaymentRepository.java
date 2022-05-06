package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
