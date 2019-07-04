package br.ifpe.web2.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ifpe.web2.model.Cargo;

@Repository
public interface CargoDAO extends JpaRepository<Cargo, Integer> {

//	@Query(value = "SELECT * FROM cargo ORDER BY :sort ASC LIMIT 10")
//	List<Cargo> findAllOrderParameterAndLimitTen(Sort sort);
}
