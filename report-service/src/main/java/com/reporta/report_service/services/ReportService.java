package com.reporta.report_service.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.reporta.report_service.models.dto.ReportCreateDto;
import com.reporta.report_service.models.dto.ReportResponseDto;
import com.reporta.report_service.models.dto.ReportUpdateStatus;

public interface ReportService {

        ReportResponseDto guardarReporte(ReportCreateDto report, MultipartFile image);
        ReportResponseDto guardarReporte(ReportCreateDto report);
        Long eliminarReporte(Long id);
        ReportResponseDto actualizarReporte(Long id, ReportCreateDto report);
        ReportResponseDto obtenerReportePorId(Long id);
        ReportResponseDto actualizarEstadoReporte(ReportUpdateStatus reportUpdateStatus);
        List<ReportResponseDto> obtenerReportesPorUsuario(Long idUser);
        List<ReportResponseDto> obtenerPorMunicipio(String municipio);
} 
