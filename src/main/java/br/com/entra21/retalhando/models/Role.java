package br.com.entra21.retalhando.models;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
public class Role implements GrantedAuthority {

	@Id
	private String nomeRole;
	@ManyToMany
	private List<Usuario> usuarios;

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String getAuthority() {

		return this.nomeRole;
	}

}
