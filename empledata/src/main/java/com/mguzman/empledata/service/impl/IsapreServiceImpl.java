package com.mguzman.empledata.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mguzman.empledata.model.Isapre;
import com.mguzman.empledata.repository.IsapreRepository;
import com.mguzman.empledata.service.IsapreService;

@Service
public class IsapreServiceImpl implements IsapreService{
	
	@Autowired 
	private IsapreRepository isapreRepository;

	@Override
	public Isapre findById(Integer id) throws Exception {
		Optional<Isapre> op = isapreRepository.findById(id);
		if (!op.isPresent()) {
			throw new Exception("ISAPRE NO ENCONTRADA" + id);
		}
		return op.get();
	}

	@Override
	public List<Isapre> findAll() {
		return isapreRepository.findAll();
	}

	@Override
	public Isapre save(Isapre obj) {
		isapreRepository.save(obj);
		return obj;
	}

	@Override
	public Isapre update(Isapre obj) {
		return isapreRepository.save(obj);
	}

	@Override
	public boolean deleteById(Integer id) throws Exception {
		Optional<Isapre> obj = isapreRepository.findById(id);
		if (!obj.isPresent()) {
			throw new Exception("ISAPRE NO ENCONTRADA" + id);
		}
		isapreRepository.deleteById(id);
		return true;
	}

}
