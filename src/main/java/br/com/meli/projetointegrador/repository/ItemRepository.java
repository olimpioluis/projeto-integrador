package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
