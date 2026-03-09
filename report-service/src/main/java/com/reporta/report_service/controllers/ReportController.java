package com.reporta.report_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reporta.report_service.models.dto.ReportCreateDto;
import com.reporta.report_service.models.dto.ReportResponseDto;
import com.reporta.report_service.models.dto.ReportUpdateStatus;
import com.reporta.report_service.services.ReportService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/usuarios/{id}")
    public List<ReportResponseDto> obtenerReportesPorUsuario(@PathVariable Long id) {
        return reportService.obtenerReportesPorUsuario(id);
    }

    @PostMapping
    public ReportResponseDto agregarReporte(@RequestBody ReportCreateDto report) {
        return reportService.guardarReporte(report);
    }

    @PutMapping("/actualizar/estatus")
    public ResponseEntity<ReportResponseDto> actualizarEstatus(
        @RequestBody ReportUpdateStatus actualizacionReporte){

        ReportResponseDto respuesta = reportService.actualizarEstadoReporte(actualizacionReporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    
    }

    @GetMapping("/municipio/{municipio}")
    public List<ReportResponseDto> reporteEncargado(@PathVariable String municipio){
        return reportService.obtenerPorMunicipio(municipio);
    }

    

}
