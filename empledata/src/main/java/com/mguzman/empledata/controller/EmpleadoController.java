package com.mguzman.empledata.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.mguzman.empledata.dto.EmpleadoDTO;
import com.mguzman.empledata.model.Empleado;
import com.mguzman.empledata.service.EmpleadoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService service;
		
	@GetMapping
	@ApiOperation(value="Listar todos los trabajadores", 
			notes="Listado de todos los trabajadores registrados en la base de datos")
	@ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron empleados en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	
	public ResponseEntity<List<Empleado>> listar() {
		List<Empleado> lista = service.findAll();
		return new ResponseEntity<List<Empleado>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Buscar de trabajador por ID", 
			notes="Busqueda de trabajador por ID")

	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code=400, message = "Bad request"),
		@ApiResponse(code = 405, message = "No se encontró el empleado"),
	})
	public ResponseEntity<Empleado> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Empleado obj = service.findById(id);
		return new ResponseEntity<Empleado>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value="Registrar trabajador", 
			notes="Registro de nuevo trabajador")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	public ResponseEntity<Object> registrar(@Valid @RequestBody Empleado empleado) {
		@SuppressWarnings("unused")
		Empleado obj = service.save(empleado);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empleado.getIdEmpleado()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ApiOperation(value="Modificar trabajador",
			notes="Modificacion de trabajador ya registrado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	public ResponseEntity<Empleado> modificar(@Valid @RequestBody Empleado empleado) {
		Empleado obj = service.update(empleado);
		return new ResponseEntity<Empleado>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Eliminar trabajador por ID", 
			notes="Eliminacion de trabajador por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
	})
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
		service.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
/*	@GetMapping(value = "/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmpleadoDTO> listarHateoas() {
		List<Empleado> empleados = new ArrayList<>();
		List<EmpleadoDTO> empleadosDto = new ArrayList<>();
		empleados = service.findAll();

		for (Empleado empleado : empleados) {
			EmpleadoDTO e = new EmpleadoDTO();
			e.setIdEmpleado(empleado.getIdEmpleado());
			e.setCargo(empleado.getCargo());

			ControllerLinkBuilder linkTo = linkTo(methodOn(EmpleadoController.class).listarPorId((empleado.getIdEmpleado())));
			e.add(linkTo.withSelfRel());

			ControllerLinkBuilder linkTo1 = linkTo(
					methodOn(CargoController.class).listarPorId((empleado.getCargo().getIdCargo())));
			e.add(linkTo1.withSelfRel());

			empleadosDto.add(e);

		}
	
	}
*/
}
