package com.example.demo.infraestructura.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Producto;



public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {



}