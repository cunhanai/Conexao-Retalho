package br.com.entra21.retalhando.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "cnpj")
public class Empresa extends Instituicao {

	@Id
	private String cnpj;
	@OneToMany
	private List<Retalho> retalhos;

	public List<Retalho> getRetalhos() {
		return retalhos;
	}

	public void setRetalhos(List<Retalho> retalhos) {
		this.retalhos = retalhos;
	}

}
