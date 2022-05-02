package br.com.meli.projetointegrador.repository;

import java.util.Optional;

import br.com.meli.projetointegrador.model.ERole;
import br.com.meli.projetointegrador.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}