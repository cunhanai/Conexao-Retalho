package br.com.entra21.retalhando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.retalhando.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {

	Empresa findByCnpj(String string);
}
