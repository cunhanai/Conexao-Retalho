package br.com.entra21.conexaoretalho.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class AgendaColeta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank
	private String data;

	@NotBlank
	private String hora;

	private boolean coletado = false;

	@ManyToOne
	private Empresa empresa;

	@OneToOne
	private Retalho retalho;

	@ManyToOne
	private Ong ong;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Retalho getRetalho() {
		return retalho;
	}

	public void setRetalho(Retalho retalho) {
		this.retalho = retalho;
	}

	public Ong getOng() {
		return ong;
	}

	public void setOng(Ong ong) {
		this.ong = ong;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isColetado() {
		return coletado;
	}

	public void setColetado(boolean coletado) {
		this.coletado = coletado;
	}

}
