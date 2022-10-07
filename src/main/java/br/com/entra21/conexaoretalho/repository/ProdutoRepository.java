package br.com.entra21.conexaoretalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.conexaoretalho.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

}
