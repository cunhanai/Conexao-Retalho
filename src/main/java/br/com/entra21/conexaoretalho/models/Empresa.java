package br.com.entra21.conexaoretalho.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Empresa extends Instituicao {

	@OneToMany
	private List<Retalho> retalhos;

	@OneToMany
	private List<AgendaColeta> coletasAgendadas;

	public List<Retalho> getRetalhos() {
		return retalhos;
	}

	public void setRetalhos(List<Retalho> retalhos) {
		this.retalhos = retalhos;
	}

	public List<AgendaColeta> getColetasAgendadas() {
		return coletasAgendadas;
	}

	public void setColetasAgendadas(List<AgendaColeta> coletasAgendadas) {
		this.coletasAgendadas = coletasAgendadas;
	}

	public void addColetas(AgendaColeta coleta) {
		try {
			this.coletasAgendadas.add(coleta);
		} catch (NullPointerException npe) {
			coletasAgendadas = new ArrayList<AgendaColeta>();
			this.coletasAgendadas.add(coleta);
		}
	}
}
