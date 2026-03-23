package com.proyecto.reportes.controllers.rest;

import com.proyecto.reportes.services.UbicacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionControlador {

    @Autowired
    private UbicacionServicio ubicacionServicio;

}
