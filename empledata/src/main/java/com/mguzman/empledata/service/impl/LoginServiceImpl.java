package com.mguzman.empledata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mguzman.empledata.model.Usuario;
import com.mguzman.empledata.repository.LoginRepository;
import com.mguzman.empledata.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
    private LoginRepository repo;

    @Override
    public int cambiarClave(String clave, String nombre) {
        int rpta = 0;
        try {
            repo.cambiarClave(clave, nombre);
            rpta = 1;
        } catch (Exception e) {
            rpta = 0;
        }
        return rpta;
    }

    @Override
    public Usuario verificarNombreUsuario(String usuario) throws Exception {
        Usuario us = null;
        try {
            us = repo.verificarNombreUsuario(usuario);
            us = us != null ? us : new Usuario();
        } catch (Exception e) {
            us = new Usuario();
        }
        return us;
    }

}
