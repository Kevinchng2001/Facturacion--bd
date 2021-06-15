package com.example.demo.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

	/*Persona findByCedula(String cedula);

	List<Persona> findByNombreLike(String nombre);

	List<Persona> findByNombreAndApellido(String nombre, String apellido);

	List<Persona> findByNombre(String nombre);*/

	// JPQL

	@Query("select p from Producto p where p.nombre like %:nombre%")

	List<Producto> buscarPorNombreLike(@Param("nombre") String nombre);

	@Query("select p from Producto p where p.precio like %:precio%")
	List<Producto> buscarPorPrecioLike(@Param("precio") double precio);
	
	@Query("select p from Producto p where p.stock like %:stock%")


	List<Producto>buscarporList(@Param("stock") int stock);

}
