package com.example.demo.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer>{
	
	Persona findByCedula(String cedula);

}
