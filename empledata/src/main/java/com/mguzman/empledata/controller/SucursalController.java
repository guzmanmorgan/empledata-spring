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

import com.mguzman.empledata.model.Sucursal;
import com.mguzman.empledata.service.SucursalService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {
	
	@Autowired
	private SucursalService service;
	
	@GetMapping
	@ApiOperation(value="Listar todos las sucursales", 
			notes="Listado de todos las sucursales registradas en la base de datos")
	@ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron cargos en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	
	public ResponseEntity<List<Sucursal>> listar() {
		List<Sucursal> lista = service.findAll();
		return new ResponseEntity<List<Sucursal>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Buscaqueda de sucursal por ID", 
			notes="Busqueda de sucursal por ID")
	public ResponseEntity<Sucursal> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Sucursal obj = service.findById(id);
		return new ResponseEntity<Sucursal>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value="Registrar sucursales", 
			notes="Registro de nueva sucursal")
	public ResponseEntity<Object> registrar(@Valid @RequestBody Sucursal sucursal) {
		Sucursal obj = service.save(sucursal);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sucursal.getIdSucursal()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value="Modificar sucursal", 
			notes="Modificacion de sucursal ya registrada")
	public ResponseEntity<Sucursal> modificar(@Valid @RequestBody Sucursal sucursal) {
		Sucursal obj = service.update(sucursal);
		return new ResponseEntity<Sucursal>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Eliminar sucursal por ID", 
			notes="Eliminacion de sucursal por ID")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
		service.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
