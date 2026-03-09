package com.reporta.report_service.models.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ReportCreateDto {

    @NotEmpty(message = "El campo idUser no puede estar vacío")
    private Long idUser;

    @NotEmpty(message = "El campo idLocation no puede estar vacío")
    private Long idLocation;

    private String description;

}
