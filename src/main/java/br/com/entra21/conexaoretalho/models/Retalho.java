package br.com.entra21.conexaoretalho.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Retalho implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	private double qtde;

	private String cor;

	private String material;

	private boolean disponivel = true;

	@OneToOne
	private AgendaColeta coleta;

	@ManyToOne
	private Empresa empresa;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public double getQtde() {
		return qtde;
	}

	public void setQtde(double qtde) {
		this.qtde = qtde;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public synchronized String getMaterial() {
		return material;
	}

	public synchronized void setMaterial(String material) {
		this.material = material;
	}

	public AgendaColeta getColeta() {
		return coleta;
	}

	public void setColeta(AgendaColeta coleta) {
		this.coleta = coleta;
	}

}
