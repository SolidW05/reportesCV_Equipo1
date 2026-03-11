package com.reporta.report_service.models.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "report-service")
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reportes", nullable = false, unique = true )
    private Long idReport;

    @Column(name = "id_usuario", nullable = false)
    private Long idUser;

    @Column(name = "id_ubicacion", nullable = false)
    private Long idLocation;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "fecha_reporte", nullable = false)
    private LocalDateTime date;

    @Column(name = "observaciones")
    private String observaciones;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado", nullable = false)
    private Status status;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = Status.PENDIENTE;
        }

        if (date == null) {
            date = LocalDateTime.now();
        }
    }

    public enum Status {
        PENDIENTE,
        EN_PROCESO,
        RESUELTO,
        RECHAZADO
    }

}


