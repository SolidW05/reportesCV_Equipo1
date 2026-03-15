package com.reporta.report_service.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.reporta.report_service.exceptions.NotFoundObjectException;
import com.reporta.report_service.models.dto.ReportCreateDto;
import com.reporta.report_service.models.dto.ReportResponseDto;
import com.reporta.report_service.models.dto.ReportUpdateStatus;
import com.reporta.report_service.models.entity.Report;
import com.reporta.report_service.repositories.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Value("${file.uploads-dir}")
    private String uploadsDir;

    public ReportResponseDto guardarReporte(ReportCreateDto report) {
        Report newReport = new Report();
        newReport.setIdUser(report.getIdUser());
        newReport.setIdLocation(report.getIdLocation());
        newReport.setDescription(report.getDescription());
        Report savedReport = reportRepository.save(newReport);
        return mapToDto(savedReport);
    }

    public Long eliminarReporte(Long id){
        Report oldReport = reportRepository.findById(id).orElseThrow(() ->
         new NotFoundObjectException("Reporte no encontrado"));

        RestTemplate restTemplate = new RestTemplate();
        // aqui se eliminaria la ubicacion

        reportRepository.deleteById(id);
        return id;
    } 

    public ReportResponseDto actualizarReporte(Long id, ReportCreateDto report) {

        Report existingReport = reportRepository.findById(id).orElseThrow(() ->
         new NotFoundObjectException("Reporte no encontrado"));

        existingReport.setDescription(report.getDescription());
        existingReport.setDate(LocalDateTime.now());

        Report updatedReport = reportRepository.save(existingReport);

        return mapToDto(updatedReport);
    }

    public ReportResponseDto obtenerReportePorId(Long id) {
        Report report = reportRepository.findById(id).orElseThrow(() ->
         new NotFoundObjectException("Reporte no encontrado"));
        return mapToDto(report);
    }

    public ReportResponseDto actualizarEstadoReporte(
        ReportUpdateStatus reportUpdateStatus) {
        Report existingReport = reportRepository.findById(
            reportUpdateStatus.getIdReport()).orElseThrow(() ->
         new NotFoundObjectException("Reporte no encontrado"));
        
        existingReport.setStatus(reportUpdateStatus.getStatus());
        existingReport.setObservaciones(reportUpdateStatus.getObservaciones());
        Report updatedReport = reportRepository.save(existingReport);
        return mapToDto(updatedReport);
    }

    public List<ReportResponseDto> obtenerReportesPorUsuario(Long idUser) {
        List<Report> reports = reportRepository.findByIdUser(idUser);
        return reports.stream().map(this::mapToDto).toList();
    }


    public List<ReportResponseDto> obtenerPorMunicipio(String municipio) {
        // Todo: Implementar logica para obtener reportes por 
        // municipio utilizando restTemplate
        return null;
    }


    private ReportResponseDto mapToDto(Report report) {
        ReportResponseDto reportDto = new ReportResponseDto();
        reportDto.setIdReport(report.getIdReport());
        reportDto.setIdLocation(report.getIdLocation());
        reportDto.setDescription(report.getDescription());
        reportDto.setDate(report.getDate().toString());
        reportDto.setObservaciones(report.getObservaciones());
        reportDto.setStatus(report.getStatus());
        return reportDto;
    }

}
