package br.ifpe.web2.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Funcionario {
	@Id
	@NotNull(message = "Informe a Matrícula.")
	@Size(max = 8)
	@Column(length = 70, nullable = false)
	private Integer matricula;
	@NotBlank(message = "Informe o Nome.")
	@Size(max = 70)
	@Column(length = 70, nullable = false, unique = true)
	private String nome;
	@CPF(message = "Informe CPF.")
	@Size(max = 11)
	@Column(length = 11, nullable = false)
	private String cpf;
	@NotNull(message = "Data de Nascimento informada inválida.")
	@Temporal(TemporalType.DATE)
	@PastOrPresent(message = "Data de nascimento informada inválida.")
	private Date dataNascimento;
	@NotEmpty(message = "Entidade Cargo sem dados para seleção.")
	@ManyToOne
	private Cargo cargo;
	@NotEmpty(message = "Entidade Empresa sem dados para seleção.")
	@ManyToOne
	private Empresa empresa;

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
