package com.mguzman.empledata.service;

import com.mguzman.empledata.model.Usuario;

public interface LoginService {

	Usuario verificarNombreUsuario(String usuario) throws Exception;
	int cambiarClave(String clave, String nombre) throws Exception;

}
