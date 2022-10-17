package br.com.entra21.conexaoretalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.conexaoretalho.models.Empresa;
import br.com.entra21.conexaoretalho.models.Retalho;

public interface RetalhoRepository extends JpaRepository<Retalho, String> {

	Retalho findByCodigo(long codigo);
	
	Iterable<Retalho> findByEmpresa(Empresa empresa);

//	Retalho findByCodigo(String idLong);

}
