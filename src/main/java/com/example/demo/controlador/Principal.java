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
				try {
					// Gererar el objeto Persona pidiendo los datos por teclado
					System.out.println("Ingrese el nombre: ");
					String nombre = entradaEscaner.next();
					System.out.println("Ingrese el apellido: ");
					String apellido = entradaEscaner.next();
					System.out.println("Ingrese el teléfono: ");
					String telefono = entradaEscaner.next();
					System.out.println("Ingrese la cédula: ");
					String cedula = entradaEscaner.next();

					int a, s = 0, ac, r = 0, ult;
					for (int i = 0; i < cedula.length() - 1; i++) {
						a = Integer.parseInt(cedula.charAt(i) + "");
						if (i % 2 == 0) {
							a = a * 2;
							if (a > 9) {
								a = a - 9;
							}
						}
						s = s + a;
						if (s % 10 != 0) {
							ac = ((s / 10) + 1) * 10;
							r = ac - s;
						}
						ult = Integer.parseInt(cedula.charAt(9) + "");

						if (ult == r) {
							System.out.println("La cedula ingresada es correcta");
						}
					}

					Persona p = new Persona(nombre, apellido, telefono, cedula);
					personaRepositorio.save(p);
				} catch (Exception e) {
					System.out.println("La celula ingresada es incorrecta");
				}
				break;
			case 2:
				try {
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
				} catch (Exception e) {
					System.out.println("La celula ingresada no existe");
				}
				break;
			case 3:
				try {
					System.out.println("Ingrese el nombre que quiera eliminar: ");
					String nomb = entradaEscaner.next();
					List<Persona> personanom = personaRepositorio.findByNombre(nomb);
					System.out.println("Id|" + "\t" + "|Cedula|" +"\t" + "|Nombre|" + "\t" + "|Apellido|");
					for (Persona persona : personanom) {
						System.out.println(persona.getId()+ " " +persona.getCedula() + " " + persona.getNombre() + " " + persona.getApellido());
					}
					System.out.println("Ingrese el id que quiera eliminar: ");
					int id = entradaEscaner.nextInt();
					Persona personaid = personaRepositorio.getOne(id);
					personaRepositorio.delete(personaid);

					// 1 Pedir al usuario que persona quiero modificar "Pedir la cédula"
					// 2 ENcontrar a la persona por el número de la cédula
					// 3 Eliminar

				} catch (Exception e) {
					System.out.println("Existe un problema con la cédula ingresada ");
				}
				break;
			case 4:
				// Buscar todas las personas que están en la BD e imprimir os resultados
				System.out.println("Ingrese el nombre que desee buscar: ");
				String nom = entradaEscaner.next();
				List<Persona> personas = personaRepositorio.findByNombre(nom);
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