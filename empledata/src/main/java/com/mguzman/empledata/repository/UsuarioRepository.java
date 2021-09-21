package com.mguzman.empledata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mguzman.empledata.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	//select * from usuario where username = ?
	Usuario findOneByUserName(String username);

}
