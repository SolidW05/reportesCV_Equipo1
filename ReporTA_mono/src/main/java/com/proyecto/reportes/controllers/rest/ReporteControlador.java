package com.proyecto.reportes.controllers.rest;

import com.proyecto.reportes.models.DTO.ReporteActualizarEstadoDTO;
import com.proyecto.reportes.models.DTO.ReporteCrearDTO;
import com.proyecto.reportes.models.DTO.ReporteRespuestaDTO;
import com.proyecto.reportes.services.ReporteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
public class ReporteControlador {

    @Autowired
    private ReporteServicio reporteServicio;

    @GetMapping
    public List<ReporteRespuestaDTO> reporteList(){
        return reporteServicio.reportesLimpios();
    }

    @GetMapping("/usuarios/{id}")
    public List<ReporteRespuestaDTO> reportesUsuario(@PathVariable Integer id){
        return reporteServicio.reportesPorUsuario(id);
    }

    @GetMapping("/encargado/{id}")
    public List<ReporteRespuestaDTO> reporteEncargado(@PathVariable Integer id){
        return reporteServicio.reportesPorEncargado(id);
    }

    @GetMapping("/{id}")
    public ReporteRespuestaDTO reportesPorId(@PathVariable Integer id){
        return reporteServicio.reportePorId(id);
    }

    @PostMapping("/actualizar/estatus")
    public ResponseEntity<Integer> actualizarEstatus(@RequestBody ReporteActualizarEstadoDTO actualizacionReporte){
        Integer idReporte = reporteServicio.actualizarEstado(actualizacionReporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(idReporte);

    }
    @PostMapping
    public ResponseEntity<Integer> agregarReporte(@RequestBody ReporteCrearDTO nuevoReporte){
        Integer idReporte = reporteServicio.crearReporte(nuevoReporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(idReporte);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> eliminarReporte(@PathVariable Integer id){
        Integer idReporteEliminado = reporteServicio.eliminarReporte(id);
        return ResponseEntity.status(HttpStatus.OK).body(idReporteEliminado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> actualizarReporte(@PathVariable Integer id, @RequestBody ReporteCrearDTO reporteAct){
        Integer idReporte = reporteServicio.actualizarReporte(id, reporteAct);
        return ResponseEntity.status(HttpStatus.CREATED).body(idReporte);
    }


}
