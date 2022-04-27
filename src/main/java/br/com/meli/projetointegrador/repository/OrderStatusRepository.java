package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
