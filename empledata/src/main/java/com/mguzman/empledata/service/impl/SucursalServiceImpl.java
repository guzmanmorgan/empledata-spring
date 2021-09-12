package com.mguzman.empledata.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mguzman.empledata.model.Sucursal;
import com.mguzman.empledata.repository.SucursalRepository;
import com.mguzman.empledata.service.SucursalService;

@Service
public class SucursalServiceImpl implements SucursalService{
	
	@Autowired 
	private SucursalRepository sucursalRepository;

	@Override
	public Sucursal findById(Integer id) throws Exception {
		Optional<Sucursal> op = sucursalRepository.findById(id);
		if (!op.isPresent()) {
			throw new Exception("SUCURSAL NO ENCONTRADO" + id);
		}
		return op.get();
	}

	@Override
	public List<Sucursal> findAll() {
		return sucursalRepository.findAll();
	}

	@Override
	public Sucursal save(Sucursal obj) {
		sucursalRepository.save(obj);
		return obj;
	}

	@Override
	public Sucursal update(Sucursal obj) {
		return sucursalRepository.save(obj);
	}

	@Override
	public boolean deleteById(Integer id) throws Exception {
		Optional<Sucursal> obj = sucursalRepository.findById(id);
		if (!obj.isPresent()) {
			throw new Exception("SUCURSAL NO ENCONTRADA" + id);
		}
		sucursalRepository.deleteById(id);
		return true;
	}	

}
