package com.Ubicacion.Repository;

import com.Ubicacion.Models.Ubicacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepositorio extends JpaRepository<Ubicacion, Long> {

    @Query("SELECT u.id FROM Ubicacion u WHERE u.municipio = :municipio")
    List<Long> findIdsByMunicipio(String municipio);
}
