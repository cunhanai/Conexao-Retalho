package br.com.entra21.retalhando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.retalhando.models.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	Usuario findByLogin(String login);

}
