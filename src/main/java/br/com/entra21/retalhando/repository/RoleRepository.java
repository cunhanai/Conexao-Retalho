package br.com.entra21.retalhando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.retalhando.models.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	Role findByNomeRole(String nomeRole);
}
