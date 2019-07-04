package br.ifpe.web2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ifpe.web2.dao.CargoDAO;
import br.ifpe.web2.model.Cargo;

@Service
public class CargoService {
	@Autowired
	private CargoDAO cargoRep;
	
	public List<Cargo> listarCargos() {
		return this.cargoRep.findAll(Sort.by("nome"));
	}

	public void inserirCargo(Cargo cargo) throws Exception {
		Optional<Cargo> cargoOptional = Optional.ofNullable(this.cargoRep.findByDescricao(cargo.getNome()));
		if (cargoOptional.isPresent()) {
			throw new Exception("Já existe empresa com o nome informado");
		}
		this.cargoRep.save(cargo);
	}

}
