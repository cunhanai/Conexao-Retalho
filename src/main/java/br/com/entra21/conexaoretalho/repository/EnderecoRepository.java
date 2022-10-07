package br.com.entra21.conexaoretalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.conexaoretalho.models.Endereco;
import br.com.entra21.conexaoretalho.models.Instituicao;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {

	Iterable<Endereco> findByInstituicao(Instituicao instituicao);
}
