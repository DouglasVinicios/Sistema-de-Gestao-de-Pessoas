package br.ifpe.web2.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
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

	public void inserirFuncionario(Funcionario funcionario) throws Exception {
		if(this.funcionarioRep.findByCpfAndMatricula(funcionario.getCpf(), funcionario.getMatricula()) != null) {
			throw new FuncionarioExistsException("CPF já informado para o funcionário "+funcionario.getMatricula());
		}
		LocalDate ld = LocalDateTime.ofInstant(funcionario.getDataNascimento().toInstant(), ZoneOffset.UTC).toLocalDate();
		int idade = Period.between(LocalDate.of(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth()), LocalDate.now()).getYears();
		if(idade < 18) {
			throw new Exception("O funcionário terá que possuir, no mínimo, 18 anos de idade");
		}
		
		this.funcionarioRep.save(funcionario);
	}
}
