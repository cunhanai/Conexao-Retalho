package br.com.entra21.retalhando.models;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
//@PrimaryKeyJoinColumn(name = "cnpj")
//@DiscriminatorValue("1")
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
