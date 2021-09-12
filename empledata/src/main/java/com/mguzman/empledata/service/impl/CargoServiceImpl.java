package com.mguzman.empledata.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mguzman.empledata.model.Cargo;
import com.mguzman.empledata.repository.CargoRepository;
import com.mguzman.empledata.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService{
	
	@Autowired 
	private CargoRepository cargoRepository;

	@Override
	public Cargo findById(Integer id) throws Exception {
		Optional<Cargo> op = cargoRepository.findById(id);
		if (!op.isPresent()) {
			throw new Exception("CARGO NO ENCONTRADO" + id);
		}
		return op.get();
	}

	@Override
	public List<Cargo> findAll() {
		return cargoRepository.findAll();
	}

	@Override
	public Cargo save(Cargo obj) {
		cargoRepository.save(obj);
		return obj;
	}

	@Override
	public Cargo update(Cargo obj) {
		return cargoRepository.save(obj);
	}

	@Override
	public boolean deleteById(Integer id) throws Exception {
		Optional<Cargo> obj = cargoRepository.findById(id);
		if (!obj.isPresent()) {
			throw new Exception("CARGO NO ENCONTRADO" + id);
		}
		cargoRepository.deleteById(id);
		return true;
	}

}
