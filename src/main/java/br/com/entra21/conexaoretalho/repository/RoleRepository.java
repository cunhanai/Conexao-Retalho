package br.com.entra21.conexaoretalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.conexaoretalho.models.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	Role findByNomeRole(String nomeRole);
}
