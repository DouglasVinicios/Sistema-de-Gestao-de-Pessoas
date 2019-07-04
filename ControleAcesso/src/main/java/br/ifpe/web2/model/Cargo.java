package br.ifpe.web2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String nome;
	@NotBlank
	@Size(max = 70)
	@Column(length = 70, nullable = false)
	private String descricao;
	@NotBlank
	@Size(max = 15)
	@Column(length = 15)
	private String descricaoAbreviada;
	@NotNull
	@Column(nullable = false, columnDefinition = "bit(1) default 1")
	private boolean situacao;
}
