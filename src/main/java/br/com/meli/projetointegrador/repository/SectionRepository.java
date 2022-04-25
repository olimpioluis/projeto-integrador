package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

}
