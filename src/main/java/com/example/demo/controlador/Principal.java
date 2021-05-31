package com.example.demo.controlador;

import java.util.List;
import java.util.Scanner;

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

	@SuppressWarnings("resource")
	public void GUI() {

		Scanner entradaEscaner = new Scanner(System.in);
		int entradaTeclado;
		do {
			System.out.println("**************MENÚ PRINCIPAL*********************");
			System.out.println(" ");
			System.out.println("1. Ingresar Persona ");
			System.out.println("2. Modificar Persona ");
			System.out.println("3. Eliminar Persona ");
			System.out.println("4. Listar Personas ");
			System.out.println("5. Salir ");
			entradaTeclado = entradaEscaner.nextInt();
			switch (entradaTeclado) {
			case 1:
				// Gererar el objeto Persona pidiendo los datos por teclado
				System.out.println("Ingrese el nombre: ");
				String nombre = entradaEscaner.next();
				System.out.println("Ingrese el apellido: ");
				String apellido = entradaEscaner.next();
				System.out.println("Ingrese la cédula: ");
				String cedula = entradaEscaner.next();
				System.out.println("Ingrese el teléfono: ");
				String telefono = entradaEscaner.next();

				Persona p = new Persona(nombre, apellido, telefono, cedula);
				personaRepositorio.save(p);
				break;
			case 2:
				// 1 Pedir al usuario que persona quiero modificar "Pedir la cédula"
				System.out.println("Ingrese la cédula que quiera modificar: ");
				String c1 = entradaEscaner.next();
				// 2 ENcontrar a la persona por el número de la cédula
				Persona personamodificada = personaRepositorio.findByCedula(c1);
				// 3 modificar y cambiar los datos
				System.out.println("Ingrese la cédula nueva: ");
				String cac = entradaEscaner.next();
				personamodificada.setCedula(cac);
				personaRepositorio.save(personamodificada);

				break;
			case 3:
				try {
					System.out.println("Ingrese el número de cédula que desea eliminar: ");
					String cedulaBuscar = entradaEscaner.next();

					Persona personaRecuperada = personaRepositorio.findByCedula(cedulaBuscar);
					personaRepositorio.delete(personaRecuperada);
					// 1 Pedir al usuario que persona quiero modificar "Pedir la cédula"
					// 2 ENcontrar a la persona por el número de la cédula
					// 3 Eliminar
				} catch (Exception e) {
					System.out.println("Existe un problema con la cédula ingresada ");
				}
				break;
			case 4:

				List<Persona> personas = personaRepositorio.buscarPorNombreLike("Gabriela");
				System.out.println("Cédula|" + "\t" + "|Nombre|" + "\t" + "|Apellido|");
				for (Persona persona : personas) {
					System.out.println(persona.getCedula() + " " + persona.getNombre() + " " + persona.getApellido());
				}
				break;
			case 5:

				System.exit(0);
				break;

			default:
				break;
			}
		} while (entradaTeclado != 5);

	}

}