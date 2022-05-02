package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
