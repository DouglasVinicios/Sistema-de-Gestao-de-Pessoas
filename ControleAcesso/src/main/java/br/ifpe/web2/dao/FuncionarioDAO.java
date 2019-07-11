package br.ifpe.web2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifpe.web2.model.Funcionario;

@Repository
public interface FuncionarioDAO extends JpaRepository<Funcionario, Integer> {

	Funcionario findByCpfAndMatricula(String cpf, String matricula);
	
}