package com.reporta.report_service.models.dto;

import com.reporta.report_service.models.entity.Report;

import lombok.Data;

@Data
public class ReportUpdateStatus {

    private Long idReport;
    private Report.Status status;
    private String observaciones;

}
