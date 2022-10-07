package br.com.entra21.conexaoretalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.conexaoretalho.models.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	Usuario findByLogin(String login);

}
