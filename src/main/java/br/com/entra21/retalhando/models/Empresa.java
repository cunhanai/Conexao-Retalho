package br.com.entra21.retalhando.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Empresa extends Instituicao {

	@OneToMany
	private List<Retalho> retalhos;

	public List<Retalho> getRetalhos() {
		return retalhos;
	}

	public void setRetalhos(List<Retalho> retalhos) {
		this.retalhos = retalhos;
	}

}
