package com.mguzman.empledata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mguzman.empledata.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

}
