package br.com.entra21.retalhando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.retalhando.models.Endereco;
import br.com.entra21.retalhando.models.Instituicao;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {

	Iterable<Endereco> findByInstituicao(Instituicao instituicao);
}
