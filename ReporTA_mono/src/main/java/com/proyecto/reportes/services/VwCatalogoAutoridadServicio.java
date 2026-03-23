package com.proyecto.reportes.services;

import com.proyecto.reportes.models.VwCatalogoAutoridades;
import com.proyecto.reportes.repositories.VwCatalogoAutoridadRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VwCatalogoAutoridadServicio {
    @Autowired
    private VwCatalogoAutoridadRepositorio vwCatalogoAutoridadRepositorio;

    public List<VwCatalogoAutoridades> verCatalogo() {
        return vwCatalogoAutoridadRepositorio.findAll();
    }

}
