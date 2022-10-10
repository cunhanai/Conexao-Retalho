package br.com.entra21.conexaoretalho.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Instituicao {

	@Id
	private String cnpj;
	@NotBlank
	private String nomeInstituicao;
	
	@NotBlank
	@Email
	private String emailCadastro;
	
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

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setnomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
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

	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public void addResponsavel(Responsavel responsavel) {
		try {
			this.responsaveis.add(responsavel);
		} catch (NullPointerException npe) {
			responsaveis = new ArrayList<>();
			this.responsaveis.add(responsavel);
		}
	}

}
