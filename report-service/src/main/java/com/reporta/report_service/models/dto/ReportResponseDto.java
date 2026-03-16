package com.reporta.report_service.models.dto;

import com.reporta.report_service.models.entity.Report;

import lombok.Data;

@Data
public class ReportResponseDto {

    private Long idReport;
    private Long idLocation;
    private String description;
    private String date;
    private String observaciones;
    private Report.Status status;
    private String imageName;

}
