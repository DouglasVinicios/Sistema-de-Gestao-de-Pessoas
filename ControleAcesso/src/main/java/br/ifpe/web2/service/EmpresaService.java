package br.ifpe.web2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ifpe.web2.dao.EmpresaDAO;
import br.ifpe.web2.exception.OneEmpresaExistException;
import br.ifpe.web2.model.Empresa;

@Service
public class EmpresaService {
	@Autowired
	private EmpresaDAO empresaRep;

	public List<Empresa> listarEmpresas() {
		return this.empresaRep.findAll(Sort.by("nome"));
	}

	public Optional<Empresa> findById(Integer id) {
		return this.empresaRep.findById(id);
	}

	public Empresa findByNomeOrNomeAbreviado(String nomeEmpresa) {
		return this.empresaRep.findByNomeOrNomeAbreviado(nomeEmpresa).get(0);
	}

	public void inserirEmpresa(Empresa empresa) throws Exception {
		Optional<Empresa> empresaOptional = Optional.ofNullable(this.empresaRep.findByNome(empresa.getNome()));
		if (empresaOptional.isPresent()) {
			throw new Exception("Já existe empresa com o nome informado");
		}
		for (Empresa e : this.empresaRep.findByIndicadorEmpresaPrincipal(empresa.isIndicadorEmpresaPrincipal())) {
			if (e.isIndicadorEmpresaPrincipal()) {
				throw new Exception("Já existe empresa principal");
			}
		}
		this.empresaRep.save(empresa);
	}

	public List<Empresa> filtrarEmpresaPeloNome(String nomeEmpresa) throws OneEmpresaExistException, Exception {
		if (!nomeEmpresa.trim().isEmpty()) {
			Optional<List<Empresa>> empresas = Optional
					.ofNullable(this.empresaRep.findByNomeOrNomeAbreviado(nomeEmpresa));
			if (empresas.isPresent()) {

				if (empresas.get().size() == 1) {
					throw new OneEmpresaExistException();
				}
				if (empresas.get().size() == 0) {
					throw new Exception();
				}
				return empresas.get();
			}
		}
		return listarEmpresas();
	}
}
