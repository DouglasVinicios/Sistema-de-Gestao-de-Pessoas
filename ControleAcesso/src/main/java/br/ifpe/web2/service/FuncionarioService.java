package br.ifpe.web2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifpe.web2.acesso.excecoes.FuncionarioExistsException;
import br.ifpe.web2.dao.FuncionarioDAO;
import br.ifpe.web2.model.Funcionario;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioDAO funcionarioRep;
	
	public List<Funcionario> listarFuncionarios() {
		return this.funcionarioRep.findAll();
	}

	public void inserirFuncionario(Funcionario funcionario) throws FuncionarioExistsException {
		if(this.funcionarioRep.findByCpfAndMatricula(funcionario.getCpf(), funcionario.getMatricula()) != null) {
			throw new FuncionarioExistsException("Funcionario já existe");
		}
		this.funcionarioRep.save(funcionario);
	}
}
