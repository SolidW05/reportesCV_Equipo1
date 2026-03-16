package com.reporta.report_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.reporta.report_service.models.dto.ReportCreateDto;
import com.reporta.report_service.models.dto.ReportResponseDto;
import com.reporta.report_service.models.dto.ReportUpdateStatus;
import com.reporta.report_service.services.ImageStorageService;
import com.reporta.report_service.services.ReportService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ImageStorageService imageStorageService;

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<List<ReportResponseDto>> obtenerReportesPorUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.obtenerReportesPorUsuario(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDto> obtenerDetalleReporte(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.obtenerReportePorId(id));
    }

    // @PostMapping
    public ResponseEntity<ReportResponseDto> agregarReporte(@RequestBody ReportCreateDto report) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reportService.guardarReporte(report));
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<ReportResponseDto> agregarReporteConImagen(@RequestPart("report") ReportCreateDto report,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reportService.guardarReporte(report, image));
    }

    @PutMapping("/actualizar/estatus")
    public ResponseEntity<ReportResponseDto> actualizarEstatus(
            @RequestBody ReportUpdateStatus actualizacionReporte) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reportService.actualizarEstadoReporte(actualizacionReporte));
    }

    @GetMapping("/municipio/{municipio}")
    public List<ReportResponseDto> reporteEncargado(@PathVariable String municipio) {
        return reportService.obtenerPorMunicipio(municipio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        reportService.eliminarReporte(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportResponseDto> actualizarReporte(
            @PathVariable Long id, @RequestPart("report") ReportCreateDto actualizacionReporte,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportService.actualizarReporte(id, actualizacionReporte, image));
    }

    // Endpoint para obtener la imagen del reporte por su ID
    @GetMapping("/images/{id}")
    public ResponseEntity<UrlResource> getImage(@PathVariable Long id) throws Exception {

        UrlResource resource = imageStorageService.cargarImagen(reportService.obtenerReportePorId(id)
                .getImageName());

        return ResponseEntity.ok().body(resource);
    }

}
