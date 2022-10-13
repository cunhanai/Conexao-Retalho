package br.com.entra21.conexaoretalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.conexaoretalho.models.Instituicao;
import br.com.entra21.conexaoretalho.models.Responsavel;

public interface ResponsavelRepository extends JpaRepository<Responsavel, String> {

	Iterable<Responsavel> findByInstituicao(Instituicao instituicao);
}
