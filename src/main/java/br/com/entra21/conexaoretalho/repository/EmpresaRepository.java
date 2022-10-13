package br.com.entra21.conexaoretalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.conexaoretalho.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {

	Empresa findByCnpj(String cnpj);
}
