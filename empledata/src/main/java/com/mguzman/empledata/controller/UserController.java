package com.mguzman.empledata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mguzman.empledata.model.Usuario;
import com.mguzman.empledata.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {
	
	@Autowired
	private UsuarioService userService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar(){
		List<Usuario> lista = userService.findAll();
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> lista = userService.findAll();
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}

}
