package com.proyecto.autoridades_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.autoridades_service.model.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

} 