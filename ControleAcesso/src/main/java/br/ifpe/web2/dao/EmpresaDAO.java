package br.ifpe.web2.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ifpe.web2.model.Empresa;

@Repository
public interface EmpresaDAO extends JpaRepository<Empresa, Integer> {
	
	@Query("SELECT e FROM Empresa e WHERE e.nome LIKE %:nomeEmpresa% OR e.nomeAbreviado LIKE %:nomeEmpresa%")
	List<Empresa> findByNomeOrNomeAbreviado(String nomeEmpresa);
	
	Optional<Empresa> findByNome(String nomeEmpresa);
	
	List<Empresa> findByIndicadorEmpresaPrincipal(boolean empresaPrincipal);
}
