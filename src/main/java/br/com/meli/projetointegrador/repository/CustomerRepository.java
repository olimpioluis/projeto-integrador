package br.com.meli.projetointegrador.repository;

import br.com.meli.projetointegrador.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
