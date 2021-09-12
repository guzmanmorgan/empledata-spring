package com.mguzman.empledata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mguzman.empledata.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer>{

}
