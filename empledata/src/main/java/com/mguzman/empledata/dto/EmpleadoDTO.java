package com.mguzman.empledata.dto;

import com.mguzman.empledata.model.Cargo;

public class EmpleadoDTO {
	
	private Integer idEmpleado;
	private Cargo cargo;
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	

}
