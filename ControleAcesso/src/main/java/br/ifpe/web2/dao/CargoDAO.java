package br.ifpe.web2.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ifpe.web2.model.Cargo;
import br.ifpe.web2.model.Empresa;

@Repository
public interface CargoDAO extends JpaRepository<Cargo, Integer> {

//	@Query("SELECT c FROM Cargo c WHERE c.nome LIKE %:nomeCargo% OR c LIKE %:nomeCargo%")
//	List<Cargo> findByNomeOrNomeAbreviado(String nomeCargo);

	Cargo findByDescricao(String descricaoCargo);
	
	List<Cargo> findByDescricao(boolean cargoPrincipal);
}
