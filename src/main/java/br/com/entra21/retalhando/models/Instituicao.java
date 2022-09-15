package br.com.entra21.retalhando.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Instituicao {

	@Id
	private String cnpj;
	@NotBlank
	private String nomeEmpresa;
	@NotBlank
	@OneToOne
	private Endereco endereco;
	@OneToMany
	private List<Responsavel> responsaveis;
	private String sobre;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public String getDescricao() {
		return sobre;
	}

	public void setDescricao(String descricao) {
		this.sobre = descricao;
	}

}
