package com.mguzman.empledata.service;

import com.mguzman.empledata.model.ResetToken;

public interface ResetTokenService {
	
	ResetToken findByToken(String token);

    void guardar(ResetToken token);

    void eliminar(ResetToken token);

}
