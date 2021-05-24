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

	public void insertarPersona()
	{
		try {
		Persona p1= new Persona(2, "Sebastian", "Quevedo", "3423423", "534534534");
		System.out.print("hola");
		personaRepositorio.save(p1);
		}catch (Exception e) {
			System.out.println("Existe un errror el momento de guardar lapersoma");
		}
	}

}