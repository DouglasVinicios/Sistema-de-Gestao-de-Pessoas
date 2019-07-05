package br.ifpe.web2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifpe.web2.model.Cargo;

@Repository
public interface CargoDAO extends JpaRepository<Cargo, Integer> {

	List<Cargo> findByNome(String nomeCargo);

	Cargo findByDescricao(String descricaoCargo);
	
	List<Cargo> findByDescricao(boolean cargoPrincipal);
}
