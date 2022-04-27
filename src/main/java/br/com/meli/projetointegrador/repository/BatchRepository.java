package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findAllByProductId(Long id);
}
