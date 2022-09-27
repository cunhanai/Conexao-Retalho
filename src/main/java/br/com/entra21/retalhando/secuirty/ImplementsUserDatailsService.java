package br.com.entra21.retalhando.secuirty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.entra21.retalhando.models.Usuario;
import br.com.entra21.retalhando.repository.UsuarioRepository;

@Repository
public class ImplementsUserDatailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository ur;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = ur.findByLogin(login);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}

		return usuario;
	}

}
