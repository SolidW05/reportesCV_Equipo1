package com.reporta.report_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @GetMapping
    public String getReports() {
        return new String();
    }

    @PostMapping
    public String addNewReport(@RequestBody String entity) {
        return entity;
    }

}
