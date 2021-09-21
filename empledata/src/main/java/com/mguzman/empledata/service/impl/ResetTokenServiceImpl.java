package com.mguzman.empledata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mguzman.empledata.model.ResetToken;
import com.mguzman.empledata.repository.ResetTokenRepository;
import com.mguzman.empledata.service.ResetTokenService;

@Service
public class ResetTokenServiceImpl implements ResetTokenService {
	
	@Autowired
    private ResetTokenRepository repo;

    @Override
    public ResetToken findByToken(String token) {
        return repo.findByToken(token);
    }

    @Override
    public void guardar(ResetToken token) {
        repo.save(token);

    }

    @Override
    public void eliminar(ResetToken token) {
        repo.delete(token);
    }

}
