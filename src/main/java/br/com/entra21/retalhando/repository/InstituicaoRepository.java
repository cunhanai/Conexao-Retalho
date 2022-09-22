package br.com.entra21.retalhando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.retalhando.models.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, String> {

	Instituicao findByCnpj(long cnpj);
}
