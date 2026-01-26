package com.proyecto.reportes.repositories;

import com.proyecto.reportes.models.Autoridad;
import com.proyecto.reportes.models.DTO.AutoridadIdDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.proyecto.reportes.models.DTO.ServicioDTO;

import java.util.List;
import java.util.Map;

@Repository
public interface AutoridadRepositorio extends JpaRepository<Autoridad, Integer> {

    @Query("SELECT DISTINCT new com.proyecto.reportes.models.DTO.ServicioDTO(au.servicio) FROM Autoridad au")
    List<ServicioDTO> obtenerServicios();

    @Query("SELECT new com.proyecto.reportes.models.DTO.AutoridadIdDTO(" +
            "au.idAutoridad, au.autoridad) " +
            "FROM Autoridad au " +
            "JOIN au.municipio m " +
            "WHERE m.idMunicipio = :id AND au.servicio = :servicio " +
            "ORDER BY au.municipio ASC"
    )
    List<AutoridadIdDTO> autoridadSortByMunicipioAndServicio(
            @Param("id") Integer municipio,
            @Param("servicio") String servicio);

}
