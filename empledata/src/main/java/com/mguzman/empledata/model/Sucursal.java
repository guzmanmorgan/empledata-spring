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

@ApiModel(description = "Sucursales de la empresa")
@Entity
@Table(name = "sucursal")
public class Sucursal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sucursal")
	private Integer idSucursal;
	
	@ApiModelProperty(notes = "Nombres sucursal debe tener entre 2 y 20 caracteres")
	@Size(min = 2, max = 20, message = "El nombre de la sucursal debe contener mínimo 2 caracteres")
	@Column(name = "nombre_sucursal", nullable = false, length = 20)
	private String nombreSucursal;
	
	@ApiModelProperty(notes = "Direccion sucursal debe tener entre 2 y 20 caracteres")
	@Size(min = 2, max = 20, message = "La direccion de la sucursal debe contener mínimo 2 caracteres")
	@Column(name = "direccion_sucursal", nullable = false, length = 20)
	private String direccionSucursal;
	
	@OneToMany(mappedBy = "sucursal", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value={"sucursal"})
	private List<Empleado> empleado;

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	public List<Empleado> getEmpleado() {
		return empleado;
	}

	public void setEmpleado(List<Empleado> empleado) {
		this.empleado = empleado;
	}
	
	

}
