package com.proyecto.reportes.models.DTO;

import com.proyecto.reportes.models.Reporte;

public class ReporteActualizarEstadoDTO {

    public ReporteActualizarEstadoDTO(Integer idReporte, Reporte.EstadoReporte estatus, String observaciones) {
        this.idReporte = idReporte;
        this.estatus = estatus;
        this.observaciones = observaciones;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Reporte.EstadoReporte getEstatus() {
        return estatus;
    }

    public void setEstatus(Reporte.EstadoReporte estatus) {
        this.estatus = estatus;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    private Integer idReporte;
    private Reporte.EstadoReporte estatus;
    private String observaciones;
}
