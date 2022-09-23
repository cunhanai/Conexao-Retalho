package br.com.entra21.retalhando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.retalhando.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

}
