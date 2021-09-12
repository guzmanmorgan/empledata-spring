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

import com.mguzman.empledata.model.Cargo;
import com.mguzman.empledata.service.CargoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoService service;
	
	@GetMapping
	@ApiOperation(value="Listar todos los cargos", 
			notes="Listado de todos los cargos registrados en la base de datos")
	@ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron cargos en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	
	public ResponseEntity<List<Cargo>> listar() {
		List<Cargo> lista = service.findAll();
		return new ResponseEntity<List<Cargo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Buscar de cargo por ID", 
			notes="Busqueda de cargo por ID")
	public ResponseEntity<Cargo> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Cargo obj = service.findById(id);
		return new ResponseEntity<Cargo>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value="Registrar cargos", 
			notes="Registro de nuevo cargo")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Cargo cargo) {
		@SuppressWarnings("unused")
		Cargo obj = service.save(cargo);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cargo.getIdCargo()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value="Modificar cargo", 
			notes="Modificacion de cargo ya registrado")
	public ResponseEntity<Cargo> modificar(@Valid @RequestBody Cargo cargo) {
		Cargo obj = service.update(cargo);
		return new ResponseEntity<Cargo>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Eliminar cargo por ID", 
			notes="Eliminacion de cargo por ID")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
		service.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
