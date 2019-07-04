package br.ifpe.web2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ifpe.web2.dao.CargoDAO;
import br.ifpe.web2.model.Cargo;

@Service
public class CargoService {
	@Autowired
	private CargoDAO cargoRep;
	
//	public List<Cargo> listarCargos() {
//		return this.cargoRep.findAllOrderParameterAndLimitTen(Sort.by("nome"));
//	}

}
