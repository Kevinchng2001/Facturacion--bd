package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.infraestructura.repositorio.PersonaRepositorio;
import com.example.demo.modelo.Persona;

@Controller

public class Principal {

	@Autowired
	PersonaRepositorio personaRepositorio;

	public Principal() {
		
	}
	
	public void insertarPersona() {
		
		Persona p1 = new Persona(15, "Kevin", "Chango", "456456", "1566484");
		personaRepositorio.save(p1);
	}
}