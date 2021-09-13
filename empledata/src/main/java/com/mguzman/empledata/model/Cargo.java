package com.mguzman.empledata.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Cargos de la empresa")
@Entity
@Table(name = "cargo")
public class Cargo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cargo")
	private Integer idCargo;
	
	@ApiModelProperty(notes = "Nombres cargo debe tener entre 2 y 20 caracteres")
	@Size(min = 2, max = 20, message = "El nombre del cargo debe contener mínimo 2 caracteres")
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombreCargo;
	
	@ApiModelProperty(notes = "Descripcion cargo debe tener entre 2 y 200 caracteres")
	@Size(min = 2, max = 200, message = "El nombre del cargo debe contener mínimo 2 caracteres")
	@Column(name = "descripcion", nullable = false, length = 200)
	private String descripcion;
	
	@OneToMany(mappedBy = "cargo", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value={"cargo"})
	private List<Empleado> empleado;

	public Integer getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public String getNombreCargo() {
		return nombreCargo;
	}

	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Empleado> getEmpleado() {
		return empleado;
	}

	public void setEmpleado(List<Empleado> empleado) {
		this.empleado = empleado;
	}
	
	

}
