package com.mguzman.empledata.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mguzman.empledata.model.Afp;
import com.mguzman.empledata.repository.AfpRepository;
import com.mguzman.empledata.service.AfpService;

@Service
public class AfpServiceImpl implements AfpService{
	
	@Autowired 
	private AfpRepository afpRepository;

	@Override
	public Afp findById(Integer id) throws Exception {
		Optional<Afp> op = afpRepository.findById(id);
		if (!op.isPresent()) {
			throw new Exception("AFP NO ENCONTRADA" + id);
		}
		return op.get();
	}

	@Override
	public List<Afp> findAll() {
		return afpRepository.findAll();
	}

	@Override
	public Afp save(Afp obj) {
		afpRepository.save(obj);
		return obj;
	}

	@Override
	public Afp update(Afp obj) {
		return afpRepository.save(obj);
	}

	@Override
	public boolean deleteById(Integer id) throws Exception {
		Optional<Afp> obj = afpRepository.findById(id);
		if (!obj.isPresent()) {
			throw new Exception("AFP NO ENCONTRADA" + id);
		}
		afpRepository.deleteById(id);
		return true;
	}

}
