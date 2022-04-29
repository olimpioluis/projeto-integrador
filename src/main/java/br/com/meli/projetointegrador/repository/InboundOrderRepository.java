package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {
}
