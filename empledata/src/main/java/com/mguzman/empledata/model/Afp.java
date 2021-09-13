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

@ApiModel(description = "Administradoras de prevision social")
@Entity
@Table(name = "afp")
public class Afp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_afp")
	private Integer idAfp;
	
	@ApiModelProperty(notes = "Nombres AFP debe tener entre 2 y 20 caracteres")
	@Size(min = 2, max = 20, message = "El nombre de la AFP debe contener mínimo 2 caracteres")
	@Column(name = "nombre_afp", nullable = false, length = 20)
	private String nombreAfp;
	
	@OneToMany(mappedBy = "afp", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value={"afp"})
	private List<Empleado> empleado;

	public Integer getIdAfp() {
		return idAfp;
	}

	public void setIdAfp(Integer idAfp) {
		this.idAfp = idAfp;
	}

	public String getNombreAfp() {
		return nombreAfp;
	}

	public void setNombreAfp(String nombreAfp) {
		this.nombreAfp = nombreAfp;
	}

	public List<Empleado> getEmpleado() {
		return empleado;
	}

	public void setEmpleado(List<Empleado> empleado) {
		this.empleado = empleado;
	}
	
		
}
