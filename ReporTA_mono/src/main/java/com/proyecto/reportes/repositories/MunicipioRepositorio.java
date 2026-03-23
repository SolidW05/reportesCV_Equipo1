package com.proyecto.reportes.repositories;

import com.proyecto.reportes.models.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepositorio extends JpaRepository<Municipio, Integer> {

}
