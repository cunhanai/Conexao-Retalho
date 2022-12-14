package br.com.entra21.conexaoretalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.conexaoretalho.models.Ong;
import br.com.entra21.conexaoretalho.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

	Iterable<Produto> findByOng(Ong ong);

	Produto findById(long id);

}
