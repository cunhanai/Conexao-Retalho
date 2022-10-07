package br.com.entra21.conexaoretalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entra21.conexaoretalho.models.Ong;

public interface OngRepository extends JpaRepository<Ong, String> {

}
