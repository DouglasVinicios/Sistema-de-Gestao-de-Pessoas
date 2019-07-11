package br.ifpe.web2.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Funcionario {
	@Id
	@NotNull(message = "Informe a Matrícula.")
	@Min(value = 8, message = "Matrícula deve ter 8 digitos")
	@Max(value = 8, message = "Matrícula deve ter 8 digitos")
	@Column(length = 8, nullable = false)
	private Integer matricula;
	@NotBlank(message = "Informe o Nome.")
	@Size(max = 70)
	@Column(length = 70, nullable = false, unique = true)
	private String nome;
	@CPF(message = "Informe CPF.")
	@Size(max = 11)
	@Column(length = 11, nullable = false, columnDefinition = "char(11)")
	private String cpf;
	@NotNull(message = "Data de Nascimento informada inválida.")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "Data de nascimento informada inválida.")
	private Date dataNascimento;
	@NotNull(message = "Entidade Cargo sem dados para seleção.")
	@ManyToOne
	private Cargo cargo;
	@NotNull(message = "Entidade Empresa sem dados para seleção.")
	@ManyToOne
	private Empresa empresa;
	@NotNull(message = "Informe o salário.")
	@Min(value = 0)
	private Double salario;
	@NotNull(message = "Informe a data de admissão do funcionário.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAdmissao;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDemissao;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate periodoFerias;
	@Embedded
	private Endereco endereco;
	@Lob
	private byte[] foto;

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

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public LocalDate getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(LocalDate dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public LocalDate getPeriodoFerias() {
		return periodoFerias;
	}

	public void setPeriodoFerias(LocalDate periodoFerias) {
		this.periodoFerias = periodoFerias;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] bytes) {
		this.foto = bytes;
	}

}
