package com.reporta.report_service.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.reporta.report_service.models.dto.ReportCreateDto;
import com.reporta.report_service.models.dto.ReportResponseDto;
import com.reporta.report_service.models.dto.ReportUpdateStatus;
import com.reporta.report_service.security.JwtUser;

public interface ReportService {

        ReportResponseDto guardarReporte(ReportCreateDto report, MultipartFile image);

        ReportResponseDto guardarReporte(ReportCreateDto report);

        Long eliminarReporte(Long id);

        ReportResponseDto actualizarReporte(Long id, ReportCreateDto report, MultipartFile image, JwtUser user);

        ReportResponseDto obtenerReportePorId(Long id, JwtUser user);

        ReportResponseDto actualizarEstadoReporte(ReportUpdateStatus reportUpdateStatus, JwtUser user);

        List<ReportResponseDto> obtenerReportesPorUsuario(Long idUser);

        List<ReportResponseDto> obtenerPorMunicipio(String municipio);

        String obtenerNombreImagenPorReporteId(Long id);
}
