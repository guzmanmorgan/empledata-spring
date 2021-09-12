package com.mguzman.empledata.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Empresas de seguro de salud")
@Entity
@Table(name = "isapre")
public class Isapre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_isapre")
	private Integer idIsapre;
	
	@ApiModelProperty(notes = "Nombre Isapre debe tener entre 2 y 20 caracteres")
	@Size(min = 2, max = 20, message = "El nombre de la Isapre debe contener mínimo 2 caracteres")
	@Column(name = "nombre_isapre", nullable = false, length = 20)
	private String nombreIsapre;
	
	@OneToMany(mappedBy = "isapre", cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JsonIgnoreProperties(value={"isapre"})
	private List<Empleado> empleado;

	public Integer getIdIsapre() {
		return idIsapre;
	}

	public void setIdIsapre(Integer idIsapre) {
		this.idIsapre = idIsapre;
	}

	public String getNombreIsapre() {
		return nombreIsapre;
	}

	public void setNombreIsapre(String nombreIsapre) {
		this.nombreIsapre = nombreIsapre;
	}

	public List<Empleado> getEmpleado() {
		return empleado;
	}

	public void setEmpleado(List<Empleado> empleado) {
		this.empleado = empleado;
	}
	
	

}
