package com.mguzman.empledata.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mguzman.empledata.model.Empleado;
import com.mguzman.empledata.repository.EmpleadoRepository;
import com.mguzman.empledata.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
	
	@Autowired 
	private EmpleadoRepository empleadoRepository;

	@Override
	public Empleado findById(Integer id) throws Exception {
		Optional<Empleado> op = empleadoRepository.findById(id);
		if (!op.isPresent()) {
			throw new Exception("EMPLEADO NO ENCONTRADO" + id);
		}
		return op.get();
	}

	@Override
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}

	@Override
	public Empleado save(Empleado obj) {
		empleadoRepository.save(obj);
		return obj;
	}

	@Override
	public Empleado update(Empleado obj) {
		return empleadoRepository.save(obj);
	}

	@Override
	public boolean deleteById(Integer id) throws Exception {
		Optional<Empleado> obj = empleadoRepository.findById(id);
		if (!obj.isPresent()) {
			throw new Exception("EMPLEADO NO ENCONTRADO" + id);
		}
		empleadoRepository.deleteById(id);
		return true;
	}	

}
