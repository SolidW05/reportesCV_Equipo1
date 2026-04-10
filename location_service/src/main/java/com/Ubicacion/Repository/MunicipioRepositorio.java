package com.Ubicacion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ubicacion.Models.Municipio;

public interface MunicipioRepositorio extends JpaRepository<Municipio, Integer> {
 
    boolean existsByMunicipio(String municipio);

    List<Integer> findIdsByMunicipio(String municipio);
}
