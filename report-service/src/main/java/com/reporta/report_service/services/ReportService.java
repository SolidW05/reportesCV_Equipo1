package com.reporta.report_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reporta.report_service.repositories.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

}
