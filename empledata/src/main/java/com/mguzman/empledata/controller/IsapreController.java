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

import com.mguzman.empledata.model.Isapre;
import com.mguzman.empledata.service.IsapreService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/isapres")
public class IsapreController {
	
	@Autowired
	private IsapreService service;
	
	@GetMapping
	@ApiOperation(value="Listar todas las Isapres", 
			notes="Listado de todos las Isapres registrados en la base de datos")
	@ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron Isapres en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	
	public ResponseEntity<List<Isapre>> listar() {
		List<Isapre> lista = service.findAll();
		return new ResponseEntity<List<Isapre>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Buscar Isapre por ID", 
			notes="Busqueda de Isapre por ID")
	public ResponseEntity<Isapre> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Isapre obj = service.findById(id);
		return new ResponseEntity<Isapre>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value="Registrar Isapre", 
			notes="Registro de nueva Isapre")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Isapre isapre) {
		Isapre obj = service.save(isapre);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(isapre.getIdIsapre()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value="Modificar Isapre",
			notes="Modificacion de Isapre ya registrada")
	public ResponseEntity<Isapre> modificar(@Valid @RequestBody Isapre isapre) {
		Isapre obj = service.update(isapre);
		return new ResponseEntity<Isapre>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Eliminar Isapre por ID", 
			notes="Eliminacion de Isapre por ID")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
		service.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
