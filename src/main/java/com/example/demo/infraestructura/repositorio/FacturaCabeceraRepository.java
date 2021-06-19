package com.example.demo.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.FacturaCabecera;

public interface FacturaCabeceraRepository extends JpaRepository<FacturaCabecera, Integer>{

}
