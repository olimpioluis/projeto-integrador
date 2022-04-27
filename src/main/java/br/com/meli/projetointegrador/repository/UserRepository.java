package br.com.meli.projetointegrador.repository;

import java.util.Optional;

import br.com.meli.projetointegrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByCpf(String cpf);
}
