package com.proyecto.reportes.services;

import com.proyecto.reportes.repositories.UbicacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbicacionServicio {

    @Autowired
    private UbicacionRepositorio ubicacionRepositorio;

}
