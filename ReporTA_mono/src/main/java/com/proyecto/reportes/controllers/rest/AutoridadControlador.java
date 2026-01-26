package com.proyecto.reportes.controllers.rest;

import com.proyecto.reportes.models.Autoridad;
import com.proyecto.reportes.models.DTO.AutoridadIdDTO;
import com.proyecto.reportes.models.DTO.ServicioDTO;
import com.proyecto.reportes.models.Municipio;
import com.proyecto.reportes.services.AutoridadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/autoridad")
public class AutoridadControlador {

    @Autowired
    private AutoridadServicio autoridadServicio;

    @GetMapping
    public List<Autoridad> obtenerAutoridad(){
        return autoridadServicio.obtenerAutoridades();
    }

    @GetMapping("/servicios")
    public List<ServicioDTO> obtenerServicios(){
        return autoridadServicio.obtenerServicios();
    }

    @GetMapping("/municipio/{id}/servicio/{servicio}")
    public List<AutoridadIdDTO> autoridadesSortByMunicipioServicio(
            @PathVariable Integer id,
            @PathVariable String servicio
    )
    {
        return autoridadServicio.autoridadSortByMunicipioServicio(id,servicio);
    }



}
