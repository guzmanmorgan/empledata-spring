package com.mguzman.empledata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Datos del trabajador")
@Entity
@Table(name = "empleado")
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado")
	private Integer idEmpleado;
	
	@ApiModelProperty(notes = "Nombres debe tener entre 2 y 20 caracteres")
	@Size(min = 2, max = 20, message = "El nombre debe contener mínimo 2 caracteres")
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	@ApiModelProperty(notes = "Apellidos debe tener entre 2 y 20 caracteres")
	@Size(min = 2, max = 20, message = "Los apellidos deben contener mínimo 2 caracteres")
	@Column(name = "apellido", nullable = false, length = 20)
	private String apellido;
	
	@ApiModelProperty(notes = "DNI debe tener entre 8 y 10 caracteres")
	@Size(min = 8, max = 10, message = "DNI deben contener entre 8 y 10 caracteres")
	@Column(name = "dni", nullable = false, length = 10, unique=true)
	private String dni;
	
	@Email
    @Column(name = "email")
    private String email;
	
	@ApiModelProperty(notes = "Direccion debe tener entre 2 y 20 caracteres")
	@Size(min = 2, max = 20, message = "La direccion debe contener mínimo 2 caracteres")
	@Column(name = "direccion", nullable = false, length = 20)
	private String direccion;
	
	@ManyToOne
    @JoinColumn(name = "id_cargo", nullable = false, foreignKey = @ForeignKey(name = "FK_empkleado_cargo"))
	@JsonIgnoreProperties(value={"empleado"})
    private Cargo cargo;
	
	@ManyToOne
    @JoinColumn(name = "id_sucursal", nullable = false, foreignKey = @ForeignKey(name = "FK_empleado_sucursal"))
	@JsonIgnoreProperties(value={"empleado"})
    private Sucursal sucursal;
	
	@ManyToOne
    @JoinColumn(name = "id_afp", nullable = false, foreignKey = @ForeignKey(name = "FK_empleado_afp"))
	@JsonIgnoreProperties(value={"empleado"})
    private Afp afp;
	
	@ManyToOne
    @JoinColumn(name = "id_isapre", nullable = false, foreignKey = @ForeignKey(name = "FK_empleado_isapre"))
	@JsonIgnoreProperties(value={"empleado"})
    private Isapre isapre;

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Afp getAfp() {
		return afp;
	}

	public void setAfp(Afp afp) {
		this.afp = afp;
	}

	public Isapre getIsapre() {
		return isapre;
	}

	public void setIsapre(Isapre isapre) {
		this.isapre = isapre;
	}
	
	

}
