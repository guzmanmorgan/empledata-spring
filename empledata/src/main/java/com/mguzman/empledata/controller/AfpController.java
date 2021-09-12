package com.mguzman.empledata.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mguzman.empledata.model.Afp;
import com.mguzman.empledata.service.AfpService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/afps")
public class AfpController {
	
	@Autowired
	private AfpService service;
	
	@GetMapping
	@ApiOperation(value="Listar todas las AFPs", 
			notes="Listado de todos las AFPs registrados en la base de datos")
	@ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron AFPs en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	
	public ResponseEntity<List<Afp>> listar() {
		List<Afp> lista = service.findAll();
		return new ResponseEntity<List<Afp>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Buscar de AFP por ID", 
			notes="Busqueda de AFP por ID")
	public ResponseEntity<Afp> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Afp obj = service.findById(id);
		return new ResponseEntity<Afp>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value="Registrar AFP", 
			notes="Registro de nueva AFP")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Afp afp) {
		@SuppressWarnings("unused")
		Afp obj = service.save(afp);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(afp.getIdAfp()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value="Modificar AFP",
			notes="Modificacion de AFP ya registrada")
	public ResponseEntity<Afp> modificar(@Valid @RequestBody Afp afp) {
		Afp obj = service.update(afp);
		return new ResponseEntity<Afp>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Eliminar AFP por ID", 
			notes="Eliminacion de AFP por ID")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
		service.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
