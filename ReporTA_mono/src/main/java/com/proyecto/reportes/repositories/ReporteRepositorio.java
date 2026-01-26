package com.proyecto.reportes.repositories;

import com.proyecto.reportes.models.DTO.ReporteRespuestaDTO;
import com.proyecto.reportes.models.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepositorio extends JpaRepository<Reporte, Integer> {

    @Query("SELECT new com.proyecto.reportes.models.DTO.ReporteRespuestaDTO(r.idReporte, r.descripcion, r.estado, " +
            "r.fechaReporte," +
            " r.foto," +
            " u.calle, u.numero, " +
            "u.colonia, u.codigoPostal, m.municipio, au.nombre, " +
            "a.autoridad, a.servicio, a.telefono, r.observaciones, m.idMunicipio, a.idAutoridad, u.latitud, u.longitud) " +
            "FROM Reporte r " +
            "JOIN r.ubicacion u " +
            "JOIN u.municipio m " +
            "JOIN r.autoridad a " +
            "JOIN a.usuario au")
    List<ReporteRespuestaDTO> obtenerReportesReducidos();

    @Query("SELECT new com.proyecto.reportes.models.DTO.ReporteRespuestaDTO(r.idReporte, r.descripcion, r.estado, " +
            "r.fechaReporte," +
            " r.foto," +
            " u.calle, u.numero, " +
            "u.colonia, u.codigoPostal, m.municipio, au.nombre, " +
            "a.autoridad, a.servicio, a.telefono, r.observaciones, m.idMunicipio, a.idAutoridad, u.latitud, u.longitud) " +
            "FROM Reporte r " +
            "JOIN r.ubicacion u " +
            "JOIN u.municipio m " +
            "JOIN r.autoridad a " +
            "JOIN a.usuario au " +
            "JOIN r.usuario usr " +
            "WHERE usr.idUsuario = :idUsuario")
    List<ReporteRespuestaDTO> obtenerReportesPorUsuario(@Param("idUsuario") Integer idUsuario);
    @Query("SELECT new com.proyecto.reportes.models.DTO.ReporteRespuestaDTO(r.idReporte, r.descripcion, r.estado, " +
            "r.fechaReporte," +
            " r.foto," +
            " u.calle, u.numero, " +
            "u.colonia, u.codigoPostal, m.municipio, au.nombre, " +
            "a.autoridad, a.servicio, a.telefono, r.observaciones, m.idMunicipio, a.idAutoridad, u.latitud, u.longitud ) " +
            "FROM Reporte r " +
            "JOIN r.ubicacion u " +
            "JOIN u.municipio m " +
            "JOIN r.autoridad a " +
            "JOIN a.usuario au " +
            "JOIN r.usuario usr " +
            "WHERE r.idReporte = :idReporte")
    ReporteRespuestaDTO obtenerReportePorId(@Param("idReporte") Integer idReporte);

    @Query("SELECT new com.proyecto.reportes.models.DTO.ReporteRespuestaDTO(r.idReporte, r.descripcion, r.estado, " +
            "r.fechaReporte," +
            " r.foto," +
            " u.calle, u.numero, " +
            "u.colonia, u.codigoPostal, m.municipio, au.nombre, " +
            "a.autoridad, a.servicio, a.telefono, r.observaciones, m.idMunicipio, a.idAutoridad, u.latitud, u.longitud ) " +
            "FROM Reporte r " +
            "JOIN r.ubicacion u " +
            "JOIN u.municipio m " +
            "JOIN r.autoridad a " +
            "JOIN a.usuario au " +
            "JOIN r.usuario usr " +
            "WHERE au.idUsuario = :idUsuario")
    List<ReporteRespuestaDTO> obtenerReportesPorAu(@Param("idUsuario") Integer idUsuario);


}
