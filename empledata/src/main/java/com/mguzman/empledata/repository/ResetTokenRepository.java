package com.mguzman.empledata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mguzman.empledata.model.ResetToken;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Integer> {
	
    //from ResetToken where token = :?
    ResetToken findByToken(String token);

}
