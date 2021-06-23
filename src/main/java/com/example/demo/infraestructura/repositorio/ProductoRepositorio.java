package com.example.demo.infraestructura.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.modelo.Producto;



public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {


	List<Producto> findByNombreLike(String nombre);


	List<Producto> findByNombre(String nombre);

	// JPQL
	
	@Query("select p from Producto p where p.nombre like %:nombre%")
	List<Producto> buscarPorNombreLike(@Param("nombre") String nombre);



}