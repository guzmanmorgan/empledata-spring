package com.mguzman.empledata.dto;

import java.io.Serializable;

import com.mguzman.empledata.model.Afp;
import com.mguzman.empledata.model.Cargo;
import com.mguzman.empledata.model.Empleado;
import com.mguzman.empledata.model.Isapre;
import com.mguzman.empledata.model.Sucursal;

public class ConsultaEmpleadoDTO implements Serializable{
	
	private Empleado empleado; 
	private Afp afp;
	private Cargo cargo; 
	private Isapre isapre; 
	private Sucursal sucursal;
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Afp getAfp() {
		return afp;
	}
	public void setAfp(Afp afp) {
		this.afp = afp;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public Isapre getIsapre() {
		return isapre;
	}
	public void setIsapre(Isapre isapre) {
		this.isapre = isapre;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	} 
	
	

}
