package com.proyecto.autoridades_service.repository;

import com.proyecto.autoridades_service.model.Autoridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoridadRepository extends JpaRepository<Autoridad, Integer> {

    List<Autoridad> findByIdUsuario(Integer idUsuario);
}