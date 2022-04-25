package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Long> {
}
