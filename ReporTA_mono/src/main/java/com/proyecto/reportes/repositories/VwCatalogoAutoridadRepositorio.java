package com.proyecto.reportes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.reportes.models.VwCatalogoAutoridades;

@Repository
public interface VwCatalogoAutoridadRepositorio extends JpaRepository<VwCatalogoAutoridades, Long> {
} 
