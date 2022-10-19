package br.com.entra21.conexaoretalho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.conexaoretalho.models.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, String> {

	Instituicao findByCnpj(String cnpj);

	List<Instituicao> findByNomeInstituicaoIgnoreCaseContaining(String nomeInstituicao);
}
