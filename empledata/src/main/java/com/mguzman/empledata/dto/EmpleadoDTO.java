package com.mguzman.empledata.dto;

import java.io.Serializable;

import com.mguzman.empledata.model.Empleado;

public class EmpleadoDTO implements Serializable{
		
	private Empleado empleado;

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
		
}
